package eu.enmeshed.model.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.enmeshed.model.attributes.ConnectorAttribute;
import eu.enmeshed.model.messages.Message;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplate;
import eu.enmeshed.model.relationships.Relationship;
import eu.enmeshed.model.request.ConnectorRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Webhook<T extends WebhookData> {

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
      property = "trigger")
  @JsonSubTypes({
    @JsonSubTypes.Type(
        value = ConnectorRequest.class,
        names = {
          WebhookTriggerNames.Consumption.INCOMING_REQUEST_RECEIVED,
          WebhookTriggerNames.Consumption.OUTGOING_REQUEST_CREATED,
          WebhookTriggerNames.Consumption.OUTGOING_REQUEST_CREATED_AND_COMPLETED,
          WebhookTriggerNames.Consumption
              .OUTGOING_REQUEST_FROM_RELATIONSHIP_CREATION_CREATED_AND_COMPLETED
        }),
    @JsonSubTypes.Type(
        value = ConnectorAttribute.class,
        names = {
          WebhookTriggerNames.Consumption.ATTRIBUTE_CREATED,
          WebhookTriggerNames.Consumption.ATTRIBUTE_DELETED,
          WebhookTriggerNames.Consumption.ATTRIBUTE_SUCCEEDED,
          WebhookTriggerNames.Consumption.ATTRIBUTE_UPDATED,
          WebhookTriggerNames.Consumption.SHARED_ATTRIBUTE_COPY_CREATED
        }),
    @JsonSubTypes.Type(
        value = RequestStatusChangedEventData.class,
        names = {
          WebhookTriggerNames.Consumption.INCOMING_REQUEST_STATUS_CHANGED,
          WebhookTriggerNames.Consumption.OUTGOING_REQUEST_STATUS_CHANGED
        }),
    @JsonSubTypes.Type(
        value = MessageProcessedEventData.class,
        names = WebhookTriggerNames.Consumption.MESSAGE_PROCESSED),
    @JsonSubTypes.Type(
        value = RelationshipTemplateProcessedEventData.class,
        names = WebhookTriggerNames.Consumption.RELATIONSHIP_TEMPLATE_PROCESSED),
    @JsonSubTypes.Type(
        value = Message.class,
        names = {
          WebhookTriggerNames.Transport.MESSAGE_RECEIVED,
          WebhookTriggerNames.Transport.MESSAGE_SENT
        }),
    @JsonSubTypes.Type(
        value = RelationshipTemplate.class,
        names = WebhookTriggerNames.Transport.PEER_RELATIONSHIP_TEMPLATE_LOADED),
    @JsonSubTypes.Type(
        value = Relationship.class,
        names = WebhookTriggerNames.Transport.RELATIONSHIP_CHANGED)
  })
  private T data;

  private WebhookTrigger trigger;
}
