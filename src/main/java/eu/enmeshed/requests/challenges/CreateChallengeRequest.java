package eu.enmeshed.requests.challenges;

import lombok.Getter;

@Getter
public class CreateChallengeRequest {

  private final String challengeType;
  private final String relationship;

  private CreateChallengeRequest(String challengeType, String relationship) {
    this.challengeType = challengeType;
    this.relationship = relationship;
  }

  public static CreateChallengeRequest relationship(String relationship) {
    return new CreateChallengeRequest("Relationship", relationship);
  }

  public static CreateChallengeRequest identity() {
    return new CreateChallengeRequest("Identity", null);
  }

  public static CreateChallengeRequest device() {
    return new CreateChallengeRequest("Device", null);
  }
}
