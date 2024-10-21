package eu.enmeshed.utils;

import eu.enmeshed.ConnectorClient;
import eu.enmeshed.model.relationshipTemplates.ArbitraryRelationshipTemplateContent;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplate;
import eu.enmeshed.model.relationships.ArbitraryRelationshipCreationContent;
import eu.enmeshed.model.relationships.ConnectorRelationship;
import eu.enmeshed.model.relationships.RelationshipStatus;
import eu.enmeshed.model.tokens.ConnectorToken;
import eu.enmeshed.requests.relationshipTemplates.CreateOwnRelationshipTemplateRequest;
import eu.enmeshed.requests.relationshipTemplates.CreateTokenForOwnRelationshipTemplateRequest;
import eu.enmeshed.requests.relationshipTemplates.LoadPeerRelationshipTemplateRequest;
import eu.enmeshed.requests.relationships.CreateRelationshipRequest;
import eu.enmeshed.requests.tokens.CreateOwnTokenRequest;
import eu.enmeshed.requests.tokens.LoadPeerTokenRequest;
import java.time.ZonedDateTime;
import java.util.HashMap;

public class TestUtils {

  public static class Relationships {

    private static ConnectorRelationship createPendingRelationship(ConnectorClient client1, ConnectorClient client2) {

      var template = RelationshipTemplates.exchangeTemplate(client1, client2);

      var creationContent = ArbitraryRelationshipCreationContent.builder().value("value").build();
      var createRelationshipResponse = client2.relationships.createRelationship(
          CreateRelationshipRequest.builder().creationContent(creationContent).templateId(template.getId()).build());

      return syncUntilHasRelationshipInStatus(client1, createRelationshipResponse.getResult().getId(), RelationshipStatus.PENDING);
    }

    public static ConnectorRelationship establishRelationship(ConnectorClient client1, ConnectorClient client2) {

      var relationship = createPendingRelationship(client1, client2);
      var acceptResponse = client1.relationships.acceptRelationship(relationship.getId());

      return syncUntilHasRelationshipInStatus(client2, acceptResponse.getResult().getId(), RelationshipStatus.ACTIVE);
    }

    public static ConnectorRelationship rejectRelationship(ConnectorClient client1, ConnectorClient client2) {

      var relationship = Relationships.createPendingRelationship(client1, client2);
      var rejectResponse = client1.relationships.rejectRelationship(relationship.getId());

      return syncUntilHasRelationshipInStatus(client2, rejectResponse.getResult().getId(), RelationshipStatus.REJECTED);
    }

    public static ConnectorRelationship revokeRelationship(ConnectorClient client1, ConnectorClient client2) {

      var relationship = Relationships.createPendingRelationship(client1, client2);
      var revokeResponse = client2.relationships.revokeRelationship(relationship.getId());

      return syncUntilHasRelationshipInStatus(client1, revokeResponse.getResult().getId(), RelationshipStatus.REVOKED);
    }

    public static ConnectorRelationship establishAndTerminateRelationship(ConnectorClient client1, ConnectorClient client2) {

      var relationship = Relationships.establishRelationship(client1, client2);
      client1.relationships.terminateRelationship(relationship.getId());

      syncUntilHasRelationshipInStatus(client1, relationship.getId(), RelationshipStatus.TERMINATED);
      syncUntilHasRelationshipInStatus(client2, relationship.getId(), RelationshipStatus.TERMINATED);

      return relationship;
    }

    public static void reactivateRelationship(ConnectorClient client1, ConnectorClient client2, String relationshipId) {

      client1.relationships.requestRelationshipReactivation(relationshipId).getResult();

      syncUntilHasRelationshipInStatus(client1, relationshipId, RelationshipStatus.TERMINATED);
      syncUntilHasRelationshipInStatus(client2, relationshipId, RelationshipStatus.TERMINATED);
    }

    public static void rejectReactivationOfRelationship(ConnectorClient rejectingClient, ConnectorClient peerClient, String relationshipId) {
      rejectingClient.relationships.rejectRelationshipReactivation(relationshipId).getResult();

      syncUntilHasRelationshipInStatus(rejectingClient, relationshipId, RelationshipStatus.TERMINATED);
      syncUntilHasRelationshipInStatus(peerClient, relationshipId, RelationshipStatus.TERMINATED);
    }

    public static void revokeReactivationOfRelationship(ConnectorClient revokingClient, ConnectorClient peerClient, String relationshipId) {
      revokingClient.relationships.revokeRelationshipReactivation(relationshipId).getResult();

      syncUntilHasRelationshipInStatus(revokingClient, relationshipId, RelationshipStatus.TERMINATED);
      syncUntilHasRelationshipInStatus(peerClient, relationshipId, RelationshipStatus.TERMINATED);
    }

    public static void acceptReactivationOfRelationship(ConnectorClient acceptingClient, ConnectorClient peerClient, String relationshipId) {
      acceptingClient.relationships.acceptRelationshipReactivation(relationshipId).getResult();

      syncUntilHasRelationshipInStatus(acceptingClient, relationshipId, RelationshipStatus.ACTIVE);
      syncUntilHasRelationshipInStatus(peerClient, relationshipId, RelationshipStatus.ACTIVE);
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
          Thread.sleep(1000 + (i * 200));
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
  }

  public static class RelationshipTemplates {

    public static RelationshipTemplate createTemplate(ConnectorClient client) {
      return client.relationshipTemplates.createOwnRelationshipTemplate(
          CreateOwnRelationshipTemplateRequest.builder()
              .content(ArbitraryRelationshipTemplateContent.builder().value("value").build())
              .maxNumberOfAllocations(1)
              .expiresAt(ZonedDateTime.now().plusDays(1)).build()).getResult();
    }

    private static ConnectorToken createTemplateToken(ConnectorClient client, String templateId) {
      return client.relationshipTemplates.createTokenForOwnRelationshipTemplate(
              templateId,
              CreateTokenForOwnRelationshipTemplateRequest.builder()
                  .expiresAt(ZonedDateTime.now().plusDays(1))
                  .build())
          .getResult();
    }

    private static void loadPeerRelationshipTemplate(ConnectorClient client, ConnectorToken token) {
      client.relationshipTemplates.loadPeerRelationshipTemplate(
          LoadPeerRelationshipTemplateRequest.builder()
              .reference(token.getTruncatedReference())
              .build())
          .getResult();
    }

    public static RelationshipTemplate exchangeTemplate(ConnectorClient clientCreator, ConnectorClient clientRecipient) {

      var template = createTemplate(clientCreator);
      var templateToken = createTemplateToken(clientCreator, template.getId());
      loadPeerRelationshipTemplate(clientRecipient, templateToken);

      return template;
    }
  }

  public static ConnectorToken exchangeToken(ConnectorClient client1, ConnectorClient client2) {
    var token = createToken(client1);

    var response = client2.tokens.loadPeerToken(LoadPeerTokenRequest.builder().reference(token.getTruncatedReference()).build());
    return response.getResult();
  }

  public static ConnectorToken createToken(ConnectorClient client) {
    var token = client.tokens.createOwnToken(CreateOwnTokenRequest.builder().content(new HashMap<>()).expiresAt(ZonedDateTime.now().plusDays(1)).build());

    return token.getResult();
  }
}
