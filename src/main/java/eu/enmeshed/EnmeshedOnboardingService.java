package eu.enmeshed;

import eu.enmeshed.client.EnmeshedClient;
import eu.enmeshed.model.AttributeWrapper;
import eu.enmeshed.model.ContentWrapper;
import eu.enmeshed.model.IdentityInfo;
import eu.enmeshed.model.ResultWrapper;
import eu.enmeshed.model.attributes.IdentityAttribute;
import eu.enmeshed.model.attributes.values.AttributeValue;
import eu.enmeshed.model.attributes.values.identity.DisplayName;
import eu.enmeshed.model.qr.QrCode;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplate;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplateContent;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplateCreation;
import eu.enmeshed.model.relationships.Relationship;
import eu.enmeshed.model.relationships.RelationshipCreationContent;
import eu.enmeshed.model.requestItems.CreateAttributeRequestItem;
import eu.enmeshed.model.requestItems.ReadAttributeRequestItem;
import eu.enmeshed.model.requestItems.RequestItem;
import eu.enmeshed.model.requestItems.RequestItemGroup;
import eu.enmeshed.model.requestItems.ShareAttributeRequestItem;
import eu.enmeshed.model.responseItems.ReadAttributeAcceptResponseItem;
import eu.enmeshed.model.responseItems.ResponseItem;
import eu.enmeshed.model.responseItems.ResponseItemGroup;
import feign.Response;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnmeshedOnboardingService {
  private static final Long QR_CODE_VALIDITY_MINUTES_DEFAULT = 60L;
  private static final Integer QR_CODE_NUMBER_OF_ALLOCATIONS = 1;

  private final EnmeshedClient enmeshedClient;

  @Getter private final IdentityInfo identityInfo;

  @Getter private final AttributeWrapper connectorDisplayNameAttribute;

  private final List<Class<? extends AttributeValue>> requiredAttributes;

  private final List<Class<? extends AttributeValue>> optionalAttributes;

  private final List<Class<? extends RequestItem>> createAttributes;

  public EnmeshedOnboardingService(
      EnmeshedClient enmeshedClient,
      String connectorDisplayName,
      List<Class<? extends AttributeValue>> requiredAttributes,
      List<Class<? extends AttributeValue>> optionalAttributes) {
    this.enmeshedClient = enmeshedClient;
    this.requiredAttributes = requiredAttributes;
    this.optionalAttributes = optionalAttributes;
    this.createAttributes = List.of();

    // Get IdentifyInfo
    identityInfo = enmeshedClient.getIdentityInfo().getResult();

    // Setup Connector's Display Name
    connectorDisplayNameAttribute = setupConnectorDisplayName(connectorDisplayName);
  }

  public EnmeshedOnboardingService(
      EnmeshedClient enmeshedClient,
      String connectorDisplayName,
      List<Class<? extends AttributeValue>> requiredAttributes,
      List<Class<? extends AttributeValue>> optionalAttributes,
      List<Class<? extends RequestItem>> createAttributes) {
    this.enmeshedClient = enmeshedClient;
    this.requiredAttributes = requiredAttributes;
    this.optionalAttributes = optionalAttributes;
    this.createAttributes = createAttributes;

    // Get IdentifyInfo
    identityInfo = enmeshedClient.getIdentityInfo().getResult();

    // Setup Connector's Display Name
    connectorDisplayNameAttribute = setupConnectorDisplayName(connectorDisplayName);
  }

  private AttributeWrapper setupConnectorDisplayName(String connectorDisplayName) {
    ResultWrapper<List<AttributeWrapper>> foundAttributes =
        enmeshedClient.searchAttributes(DisplayName.class.getSimpleName());
    Optional<AttributeWrapper> displayNameAttribute =
        foundAttributes.getResult().stream()
            .filter(attribute -> attribute.getContent().getValue() instanceof DisplayName)
            .filter(
                attribute ->
                    ((DisplayName) attribute.getContent().getValue())
                        .getValue()
                        .equals(connectorDisplayName))
            .findFirst();

    if (displayNameAttribute.isPresent()) {
      log.info(
          "Display Name Attribute with Value '{}' already exist. Reusing it. (ID: {})",
          connectorDisplayName,
          displayNameAttribute.get().getId());

      return displayNameAttribute.get();
    }

    log.info(
        "Display Name Attribute with Value '{}' does not exist. Creating it.",
        connectorDisplayName);

    // Attribute not found - Create it!
    IdentityAttribute identityAttribute = new IdentityAttribute();
    identityAttribute.setValue(DisplayName.builder().value(connectorDisplayName).build());

    AttributeWrapper createdDisplayNameAttribute =
        enmeshedClient.createAttribute(ContentWrapper.containing(identityAttribute)).getResult();

    log.info("Created Display Name Attribute with ID {}", createdDisplayNameAttribute.getId());

    return createdDisplayNameAttribute;
  }

  /**
   * Generate a QR Code for setting up a new relationship to a client.
   *
   * @return Details of the registration process like the RelationshipTemplate ID which is required
   *     to keep track of the status of the registration.
   */
  public RegistrationData generateQrCodeForRegistrationAsJpg(
      String displayTextRequestedAttributes,
      String displayTextSharedAttributes,
      String displayTextCreateAttributes,
      Long qrCodeValidityMinutes) {
    RelationshipTemplate relationshipTemplate =
        createOnboardingRelationshipTemplate(
            displayTextRequestedAttributes,
            displayTextSharedAttributes,
            displayTextCreateAttributes,
            qrCodeValidityMinutes);
    Response qrCodeResponse =
        enmeshedClient.getQrCodeForRelationshipTemplate(relationshipTemplate.getId());

    try (InputStream inputStream = qrCodeResponse.body().asInputStream()) {
      return new RegistrationData(
          inputStream.readAllBytes(),
          relationshipTemplate.getId(),
          relationshipTemplate.getExpiresAt());
    } catch (IOException e) {
      log.error("Failed to read QR Code Response");
      return null;
    }
  }

  /**
   * Generate a QR Code for setting up a new relationship to a client as a model.
   *
   * @return {@link QrCode}.
   */
  public QrCode generateQrCodeForRegistration(
      String displayTextRequestedAttributes,
      String displayTextSharedAttributes,
      String displayTextCreateAttributes,
      Long qrCodeValidityMinutes) {

    RelationshipTemplate relationshipTemplate =
        createOnboardingRelationshipTemplate(
            displayTextRequestedAttributes, displayTextSharedAttributes,
            displayTextCreateAttributes, qrCodeValidityMinutes);

    return enmeshedClient.createRelationshipQrCode(relationshipTemplate.getId()).getResult();
  }

  /**
   * Checks the current state of a registration. If registration is requested by client it will be
   * accepted based on the passed acceptanceDecider.
   *
   * @param relationshipTemplateId ID of the RelationshipTemplate
   * @param acceptanceDecider Functional Interface to decide whether the incoming request should be
   *     accepted or not. The send attributes will be passed to the method call.
   * @return Registration Details and attributes shared by client during connecting.
   */
  public RegistrationResult checkRegistrationState(
      String relationshipTemplateId,
      Predicate<Map<Class<? extends AttributeValue>, AttributeValue>> acceptanceDecider) {

    enmeshedClient.sync();

    List<Relationship> relationships =
        enmeshedClient.searchRelationships(relationshipTemplateId, null, null).getResult();

    if (relationships.isEmpty()) {
      return null;
    }

    Relationship relationship = relationships.get(0);
    RelationshipCreationContent creationContent = relationship.getCreationContent();
    Map<Class<? extends AttributeValue>, AttributeValue> attributes =
        getSharedSimpleAttributesFromResponseItems(creationContent.getResponse().getItems());

    if (relationship.getStatus() == Relationship.Status.PENDING) {
      boolean decision = acceptanceDecider.test(attributes);

      if (decision) {
        enmeshedClient.acceptRelationship(relationship.getId());
      } else {
        enmeshedClient.rejectRelationship(relationship.getId());
      }
      return checkRegistrationState(relationshipTemplateId, registrationResult -> decision);
    } else if (creationContent.getResponse().getResult()
        == eu.enmeshed.model.Response.Result.ACCEPTED) {
      // Request was accepted by User and us - Get the send Attributes and return them
      return new RegistrationResult(
          attributes,
          relationships.get(0).getPeerIdentity().getAddress(),
          relationship.getId(),
          true);

    } else if (creationContent.getResponse().getResult()
        == eu.enmeshed.model.Response.Result.REJECTED) {
      // Request was accepted by User and us - Get the send Attributes and return them
      return new RegistrationResult(
          attributes,
          relationships.get(0).getPeerIdentity().getAddress(),
          relationship.getId(),
          false);
    } else {
      return null;
    }
  }

  /**
   * Checks the current state of a registration. If registration is requested by client it will be
   * accepted.
   *
   * @param relationshipTemplateId ID of the RelationshipTemplate
   * @return Registration Details and attributes shared by client during connecting.
   */
  public RegistrationResult checkRegistrationState(String relationshipTemplateId) {

    return checkRegistrationState(relationshipTemplateId, registrationResult -> true);
  }

  private Map<Class<? extends AttributeValue>, AttributeValue>
      getSharedSimpleAttributesFromResponseItems(List<ResponseItem> responseItems) {

    Map<Class<? extends AttributeValue>, AttributeValue> attributes = new HashMap<>();

    for (ResponseItem responseItem : responseItems) {
      if (responseItem instanceof ReadAttributeAcceptResponseItem readAttributeAcceptResponseItem) {
        AttributeValue attributeValue = readAttributeAcceptResponseItem.getAttribute().getValue();
        attributes.put(attributeValue.getClass(), attributeValue);
      } else if (responseItem instanceof ResponseItemGroup responseItemGroup) {
        attributes.putAll(getSharedSimpleAttributesFromResponseItems(responseItemGroup.getItems()));
      }
      // Ignore all other kinds of ResponseItems
    }

    return attributes;
  }

  private RelationshipTemplate createOnboardingRelationshipTemplate(
      String displayTextRequestedAttributes,
      String displayTextSharedAttributes,
      String displayTextCreateAttributes,
      Long qrCodeValidityMinutes) {

    RequestItemGroup sharedAttributesGroup =
        RequestItemGroup.builder()
            .title(displayTextSharedAttributes)
            .mustBeAccepted(true)
            .items(
                List.of(ShareAttributeRequestItem.fromWrapper(connectorDisplayNameAttribute, true)))
            .build();

    RequestItemGroup requestedAttributesGroup =
        RequestItemGroup.builder()
            .title(displayTextRequestedAttributes)
            .mustBeAccepted(true)
            .build();

    List<RequestItem> readAttributeItems = new ArrayList<>();
    requiredAttributes.stream()
        .map(attribute -> ReadAttributeRequestItem.withIdentityAttributeQuery(attribute, true))
        .forEach(readAttributeItems::add);
    optionalAttributes.stream()
        .map(attribute -> ReadAttributeRequestItem.withIdentityAttributeQuery(attribute, false))
        .forEach(readAttributeItems::add);
    requestedAttributesGroup.setItems(readAttributeItems);

    RequestItemGroup createAttributeGroup =
        RequestItemGroup.builder().title(displayTextCreateAttributes).mustBeAccepted(true).build();
    List<RequestItem> createAttributeItems = new ArrayList<>();
    createAttributes.stream()
        .map(
            createdAttribute ->
                CreateAttributeRequestItem.fromWrapper(connectorDisplayNameAttribute, true))
        .forEach(createAttributeItems::add);
    createAttributeGroup.setItems(createAttributeItems);

    List<RequestItemGroup> items =
        new ArrayList<>(Arrays.asList(sharedAttributesGroup, requestedAttributesGroup));

    if (!createAttributeGroup.getItems().isEmpty()) {
      items.add(createAttributeGroup);
    }

    RelationshipTemplateContent relationShipTemplateContent =
        RelationshipTemplateContent.builder()
            .onNewRelationship(RelationshipTemplateContent.ItemList.builder().items(items).build())
            .build();

    Long qrCodeValidityTime =
        Objects.isNull(qrCodeValidityMinutes)
            ? QR_CODE_VALIDITY_MINUTES_DEFAULT
            : qrCodeValidityMinutes;

    RelationshipTemplateCreation relationShipTemplateCreation =
        RelationshipTemplateCreation.builder()
            .expiresAt(ZonedDateTime.now().plusMinutes(qrCodeValidityTime))
            .maxNumberOfAllocations(QR_CODE_NUMBER_OF_ALLOCATIONS)
            .content(relationShipTemplateContent)
            .build();

    return enmeshedClient.createOwnRelationshipTemplate(relationShipTemplateCreation).getResult();
  }

  public record RegistrationData(
      byte[] qrCode, String relationshipTemplateId, ZonedDateTime expiresAt) {}

  public record RegistrationResult(
      Map<Class<? extends AttributeValue>, AttributeValue> attributes,
      String enmeshedAddress,
      String relationshipId,
      boolean accepted) {}
}
