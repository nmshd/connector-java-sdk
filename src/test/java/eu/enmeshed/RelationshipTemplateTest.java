package eu.enmeshed;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import eu.enmeshed.model.relationshipTemplates.ArbitraryRelationshipTemplateContent;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplate;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplateContentDerivation;
import eu.enmeshed.requests.relationshipTemplates.CreateOwnRelationshipTemplateRequest;
import eu.enmeshed.requests.relationshipTemplates.CreateTokenForOwnRelationshipTemplateRequest;
import eu.enmeshed.requests.relationshipTemplates.GetOwnRelationshipTemplatesQuery;
import eu.enmeshed.requests.relationshipTemplates.GetRelationshipTemplatesQuery;
import eu.enmeshed.requests.relationshipTemplates.LoadPeerRelationshipTemplateRequest;
import eu.enmeshed.utils.ConnectorContainer;
import java.time.ZonedDateTime;
import eu.enmeshed.utils.TestUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class RelationshipTemplateTest {

  @Container
  public static ConnectorContainer connector1 = new ConnectorContainer();
  @Container
  public static ConnectorContainer connector2 = new ConnectorContainer();

  private static ConnectorClient client1;
  private static ConnectorClient client2;

  @BeforeAll
  public static void setUp() {
    client1 = ConnectorClient.create(connector1.getConnectionString(), connector1.getApiKey());
    client2 = ConnectorClient.create(connector2.getConnectionString(), connector2.getApiKey());
  }

  @Test
  public void createTemplate() {

    var template = client1.relationshipTemplates.createOwnRelationshipTemplate(getCreateOwnTemplateRequest(1)).getResult();

    assertThat(template.getId(), CoreMatchers.startsWith("RLT"));
    assertThat(template.getCreatedBy(), CoreMatchers.equalTo(client1.account.getIdentityInfo().getResult().getAddress()));
  }

  @Test
  public void createTemplateWithUndefinedMaxNumberOfAllocations() {

    var template = client1.relationshipTemplates.createOwnRelationshipTemplate(getCreateOwnTemplateRequestWithUndefinedMaxNumberOfAllocations()).getResult();

    assertThat(template.getId(), CoreMatchers.startsWith("RLT"));
    assertThat(template.getMaxNumberOfAllocations(), CoreMatchers.nullValue());
  }

  @Test
  public void readTemplateWithUndefinedMaxNumberOfAllocations() {

    var createdTemplate = client1.relationshipTemplates.createOwnRelationshipTemplate(getCreateOwnTemplateRequestWithUndefinedMaxNumberOfAllocations()).getResult();
    var createdTemplateId = createdTemplate.getId();

    var response = client1.relationshipTemplates.getRelationshipTemplate(createdTemplateId).getResult();

    assertThat(response.getId(), CoreMatchers.startsWith("RLT"));
    assertThat(response.getMaxNumberOfAllocations(), CoreMatchers.nullValue());
  }

  @Test
  public void seeIfTemplateExistsInOwnTemplates() {

    var createdTemplate = client1.relationshipTemplates.createOwnRelationshipTemplate(getCreateOwnTemplateRequest(1)).getResult();

    var ownTemplates = client1.relationshipTemplates.getOwnRelationshipTemplates(GetOwnRelationshipTemplatesQuery.builder().maxNumberOfAllocations(1).build()).getResult();
    var ownTemplateIds = ownTemplates.stream().map(t -> createdTemplate.getId()).toList();

    assertThat(ownTemplateIds, CoreMatchers.hasItem(createdTemplate.getId()));
  }

  @Test
  public void seeIfTemplateExistsWhenFetchingWithId() {

    var createdTemplate = client1.relationshipTemplates.createOwnRelationshipTemplate(getCreateOwnTemplateRequest(1)).getResult();

    var fetchedTemplate = client1.relationshipTemplates.getRelationshipTemplate(createdTemplate.getId()).getResult();

    assertThat(fetchedTemplate.getId(), CoreMatchers.startsWith("RLT"));
    assertThat(fetchedTemplate.getCreatedBy(), CoreMatchers.equalTo(client1.account.getIdentityInfo().getResult().getAddress()));
  }

  @Test
  public void expectValidationErrorForSendingMaxNumberOfAllocationsIsZero() {

    var exception = assertThrows(
        Exception.class,
        () -> client1.relationshipTemplates.createOwnRelationshipTemplate(getCreateOwnTemplateRequest(0)).getResult());

    assertThat(exception.getCause().getMessage(), CoreMatchers.equalTo("maxNumberOfAllocations must be >= 1"));
  }

  @Test
  public void queryTemplates() {
    var template1 = client1.relationshipTemplates.createOwnRelationshipTemplate(getCreateOwnTemplateRequest(1)).getResult();
    var template2 = client1.relationshipTemplates.createOwnRelationshipTemplate(getCreateOwnTemplateRequest(1)).getResult();
    var template3 = client1.relationshipTemplates.createOwnRelationshipTemplate(getCreateOwnTemplateRequest(1)).getResult();

    var templates = client1.relationshipTemplates.getRelationshipTemplates(GetRelationshipTemplatesQuery.builder()
        .maxNumberOfAllocations(1)
        .isOwn(true)
        .build()).getResult();

    assertThat(templates.stream().map(RelationshipTemplate::getId).toList(), CoreMatchers.hasItems(template1.getId(), template2.getId(), template3.getId()));
  }

  @Test
  public void queryOwnTemplates() {
    var template1 = client1.relationshipTemplates.createOwnRelationshipTemplate(getCreateOwnTemplateRequest(1)).getResult();
    var template2 = client1.relationshipTemplates.createOwnRelationshipTemplate(getCreateOwnTemplateRequest(1)).getResult();
    var template3 = client1.relationshipTemplates.createOwnRelationshipTemplate(getCreateOwnTemplateRequest(1)).getResult();

    var templates = client1.relationshipTemplates.getOwnRelationshipTemplates(GetOwnRelationshipTemplatesQuery.builder()
        .maxNumberOfAllocations(1)
        .build()).getResult();

    assertThat(templates.stream().map(RelationshipTemplate::getId).toList(), CoreMatchers.hasItems(template1.getId(), template2.getId(), template3.getId()));
  }

  @Test
  public void queryPeerTemplates() {
    var templateId1 = TestUtils.RelationshipTemplates.exchangeTemplate(client1, client2).getId();
    var templateId2 = TestUtils.RelationshipTemplates.exchangeTemplate(client1, client2).getId();
    var templateId3 = TestUtils.RelationshipTemplates.exchangeTemplate(client1, client2).getId();

    var templates = client2.relationshipTemplates.getRelationshipTemplates(GetRelationshipTemplatesQuery.builder()
        .maxNumberOfAllocations(1)
        .isOwn(false)
        .build()).getResult();

    assertThat(templates.stream().map(RelationshipTemplate::getId).toList(), CoreMatchers.hasItems(templateId1, templateId2, templateId3));
  }

  private CreateOwnRelationshipTemplateRequest<RelationshipTemplateContentDerivation> getCreateOwnTemplateRequest(int maxNumberOfAllocations) {
    return
        CreateOwnRelationshipTemplateRequest.builder()
            .maxNumberOfAllocations(maxNumberOfAllocations)
            .content(ArbitraryRelationshipTemplateContent.builder().value("value").build())
            .expiresAt(ZonedDateTime.now().plusDays(1)).build();
  }

  private CreateOwnRelationshipTemplateRequest<RelationshipTemplateContentDerivation> getCreateOwnTemplateRequestWithUndefinedMaxNumberOfAllocations() {
    return CreateOwnRelationshipTemplateRequest.builder()
        .content(ArbitraryRelationshipTemplateContent.builder().value("value").build())
        .expiresAt(ZonedDateTime.now().plusDays(1)).build();
  }
}
