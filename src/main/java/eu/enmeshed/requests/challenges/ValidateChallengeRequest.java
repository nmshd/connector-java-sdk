package eu.enmeshed.requests.challenges;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ValidateChallengeRequest {

  private String challengeString;
  private String signature;
}
