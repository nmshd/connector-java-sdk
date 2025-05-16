package eu.enmeshed;

import static org.hamcrest.MatcherAssert.assertThat;

import eu.enmeshed.model.relationships.RelationshipAuditLogEntry.Reason;
import eu.enmeshed.model.relationships.RelationshipStatus;
import eu.enmeshed.utils.ConnectorContainer;
import eu.enmeshed.utils.TestUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class RelationshipTest {

  private ConnectorClient client1;
  private ConnectorClient client2;

  @BeforeEach
  public void setUp() {
    // recreate containers before each test case due to relationships created in different tests interfered with each other
    ConnectorContainer connector1 = new ConnectorContainer();
    ConnectorContainer connector2 = new ConnectorContainer();

    connector1.start();
    connector2.start();

    client1 = ConnectorClient.create(connector1.getConnectionString(), connector1.getApiKey());
    client2 = ConnectorClient.create(connector2.getConnectionString(), connector2.getApiKey());
  }

  @Test
  public void shouldCreateRelationship() {

    var relationshipId = TestUtils.Relationships.establishRelationship(client1, client2).getId();

    expectRelationshipToHaveStatus(client1, relationshipId, RelationshipStatus.ACTIVE);
    expectRelationshipToHaveStatus(client2, relationshipId, RelationshipStatus.ACTIVE);
  }

  @Test
  public void rejectRelationship() {

    var relationshipId = TestUtils.Relationships.rejectRelationship(client1, client2).getId();

    expectRelationshipToHaveStatus(client1, relationshipId, RelationshipStatus.REJECTED);
    expectRelationshipToHaveStatus(client2, relationshipId, RelationshipStatus.REJECTED);
  }

  @Test
  public void revokeRelationship() {

    var relationshipId = TestUtils.Relationships.revokeRelationship(client1, client2).getId();

    expectRelationshipToHaveStatus(client1, relationshipId, RelationshipStatus.REVOKED);
    expectRelationshipToHaveStatus(client2, relationshipId, RelationshipStatus.REVOKED);
  }

  @Test
  public void terminateAndReactivateRelationship() {

    var relationshipId = TestUtils.Relationships.establishAndTerminateRelationship(client1, client2).getId();

    expectRelationshipToHaveStatus(client1, relationshipId, RelationshipStatus.TERMINATED);
    expectRelationshipToHaveStatus(client2, relationshipId, RelationshipStatus.TERMINATED);

    TestUtils.Relationships.reactivateRelationship(client1, client2, relationshipId);
    expectRelationshipToHaveStatusAndReason(client1, relationshipId, RelationshipStatus.TERMINATED, Reason.REACTIVATION_REQUESTED);
    expectRelationshipToHaveStatusAndReason(client2, relationshipId, RelationshipStatus.TERMINATED, Reason.REACTIVATION_REQUESTED);

    TestUtils.Relationships.rejectReactivationOfRelationship(client2, client1, relationshipId);
    expectRelationshipToHaveStatusAndReason(client1, relationshipId, RelationshipStatus.TERMINATED, Reason.REJECTION_OF_REACTIVATION);
    expectRelationshipToHaveStatusAndReason(client2, relationshipId, RelationshipStatus.TERMINATED, Reason.REJECTION_OF_REACTIVATION);

    TestUtils.Relationships.reactivateRelationship(client1, client2, relationshipId);
    TestUtils.Relationships.revokeReactivationOfRelationship(client1, client2, relationshipId);
    expectRelationshipToHaveStatusAndReason(client1, relationshipId, RelationshipStatus.TERMINATED, Reason.REVOCATION_OF_REACTIVATION);
    expectRelationshipToHaveStatusAndReason(client2, relationshipId, RelationshipStatus.TERMINATED, Reason.REVOCATION_OF_REACTIVATION);

    TestUtils.Relationships.reactivateRelationship(client1, client2, relationshipId);
    TestUtils.Relationships.acceptReactivationOfRelationship(client2, client1, relationshipId);
    expectRelationshipToHaveStatusAndReason(client1, relationshipId, RelationshipStatus.ACTIVE, Reason.ACCEPTANCE_OF_REACTIVATION);
    expectRelationshipToHaveStatusAndReason(client2, relationshipId, RelationshipStatus.ACTIVE, Reason.ACCEPTANCE_OF_REACTIVATION);
  }

  private void expectRelationshipToHaveStatus(ConnectorClient client, String relationshipId, RelationshipStatus status)
  {
    var response = client.relationships.getRelationship(relationshipId).getResult();
    assertThat(response.getStatus(), CoreMatchers.equalTo(status));
  }

  private void expectRelationshipToHaveStatusAndReason(ConnectorClient client, String relationshipId, RelationshipStatus status, Reason reason)
  {
    var response = client.relationships.getRelationship(relationshipId).getResult();
    assertThat(response.getStatus(), CoreMatchers.equalTo(status));
    assertThat(response.getAuditLog().get(response.getAuditLog().size() - 1).getReason(), CoreMatchers.equalTo(reason));
  }
}
