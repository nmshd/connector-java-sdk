package eu.enmeshed.utils;

import eu.enmeshed.ConnectorClient;
import eu.enmeshed.model.relationshipTemplates.ArbitraryRelationshipTemplateContent;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplate;
import eu.enmeshed.model.relationships.ArbitraryRelationshipCreationContent;
import eu.enmeshed.model.relationships.ConnectorRelationship;
import eu.enmeshed.model.relationships.RelationshipStatus;
import eu.enmeshed.requests.relationshipTemplates.CreateOwnRelationshipTemplateRequest;
import eu.enmeshed.requests.relationshipTemplates.LoadPeerRelationshipTemplateRequest;
import eu.enmeshed.requests.relationships.CreateRelationshipRequest;
import java.time.ZonedDateTime;

public class TestUtils {

  public static ConnectorRelationship establishRelationship(ConnectorClient client1, ConnectorClient client2) {
    var template = exchangeTemplate(client1, client2);

    var creationContent = ArbitraryRelationshipCreationContent.builder().value("value").build();
    var createRelationshipResponse = client2.relationships.createRelationship(
        CreateRelationshipRequest.builder().creationContent(creationContent).templateId(template.getId()).build());

    var relationship = syncUntilHasRelationshipInStatus(client1, createRelationshipResponse.getResult().getId(), RelationshipStatus.PENDING);

    var acceptResponse = client1.relationships.acceptRelationship(relationship.getId());

    return syncUntilHasRelationshipInStatus(client2, acceptResponse.getResult().getId(), RelationshipStatus.ACTIVE);
  }

  private static ConnectorRelationship syncUntilHasRelationshipInStatus(ConnectorClient client, String id, RelationshipStatus relationshipStatus) {
    for (int i = 0; i < 10; i++) {
      client.account.sync();

      var relationship = getRelationshipSafeById(client, id);
      if (relationship == null) {
        continue;
      }

      if (relationship.getStatus() == relationshipStatus) {
        return relationship;
      }

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    throw new RuntimeException("Relationship " + id + " did not reach status " + relationshipStatus);
  }

  private static ConnectorRelationship getRelationshipSafeById(ConnectorClient client, String id) {
    try {
      return client.relationships.getRelationship(id).getResult();
    } catch (Exception e) {
      return null;
    }
  }

  public static RelationshipTemplate exchangeTemplate(ConnectorClient client1, ConnectorClient client2) {
    var template = createTemplate(client1);

    var response = client2.relationshipTemplates.loadPeerRelationshipTemplate(new LoadPeerRelationshipTemplateRequest(template.getTruncatedReference()));
    return response.getResult();
  }

  public static RelationshipTemplate createTemplate(ConnectorClient client) {
    var template = client.relationshipTemplates.createOwnRelationshipTemplate(
        CreateOwnRelationshipTemplateRequest.builder().content(ArbitraryRelationshipTemplateContent.builder().value("value").build()).expiresAt(ZonedDateTime.now().plusDays(1))
            .build());

    return template.getResult();
  }
}
