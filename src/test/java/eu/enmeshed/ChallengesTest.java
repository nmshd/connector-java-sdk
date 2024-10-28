package eu.enmeshed;

import static org.hamcrest.MatcherAssert.assertThat;

import eu.enmeshed.model.relationships.ConnectorRelationship;
import eu.enmeshed.requests.challenges.CreateChallengeRequest;
import eu.enmeshed.requests.challenges.ValidateChallengeRequest;
import eu.enmeshed.utils.ConnectorContainer;
import eu.enmeshed.utils.TestUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class ChallengesTest {

  @Container
  public static ConnectorContainer connector1 = new ConnectorContainer();

  @Container
  public static ConnectorContainer connector2 = new ConnectorContainer();

  private static ConnectorClient client1;
  private static ConnectorClient client2;
  private static ConnectorRelationship relationship;

  @BeforeAll
  public static void setUp() {
    client1 = ConnectorClient.create(connector1.getConnectionString(), connector1.getApiKey());
    client2 = ConnectorClient.create(connector2.getConnectionString(), connector2.getApiKey());

    relationship = TestUtils.establishRelationship(client1, client2);
  }

  @Test
  public void createIdentityChallenge() {
    var challenge = client1.challenges.createChallenge(CreateChallengeRequest.identity()).getResult();

    assertThat(challenge.getId(), CoreMatchers.startsWith("CHL"));
    assertThat(challenge.getType(), CoreMatchers.equalTo("Identity"));
  }

  @Test
  public void createDeviceChallenge() {
    var challenge = client1.challenges.createChallenge(CreateChallengeRequest.device()).getResult();

    assertThat(challenge.getId(), CoreMatchers.startsWith("CHL"));
    assertThat(challenge.getType(), CoreMatchers.equalTo("Device"));
  }

  @Test
  public void createRelationshipChallenge() {
    var challenge = client1.challenges.createChallenge(CreateChallengeRequest.relationship(relationship.getId())).getResult();

    assertThat(challenge.getId(), CoreMatchers.startsWith("CHL"));
    assertThat(challenge.getType(), CoreMatchers.equalTo("Relationship"));
  }

  @Test
  public void validateIdentityChallenge() {
    var challenge = client1.challenges.createChallenge(CreateChallengeRequest.identity()).getResult();

    var validationResult = client2.challenges.validateChallenge(
        ValidateChallengeRequest.builder().challengeString(challenge.getChallengeString()).signature(challenge.getSignature()).build()).getResult();

    assertThat(validationResult.isValid(), CoreMatchers.equalTo(true));
    assertThat(validationResult.getCorrespondingRelationship(), CoreMatchers.notNullValue());
    assertThat(validationResult.getCorrespondingRelationship().getPeer(), CoreMatchers.equalTo(client1.account.getIdentityInfo().getResult().getAddress()));
  }

  @Test
  public void validateRelationshipChallenge() {
    var challenge = client1.challenges.createChallenge(CreateChallengeRequest.relationship(relationship.getId())).getResult();

    var validationResult = client2.challenges.validateChallenge(
        ValidateChallengeRequest.builder().challengeString(challenge.getChallengeString()).signature(challenge.getSignature()).build()).getResult();

    assertThat(validationResult.isValid(), CoreMatchers.equalTo(true));
    assertThat(validationResult.getCorrespondingRelationship(), CoreMatchers.notNullValue());
    assertThat(validationResult.getCorrespondingRelationship().getPeer(), CoreMatchers.equalTo(client1.account.getIdentityInfo().getResult().getAddress()));
  }
}
