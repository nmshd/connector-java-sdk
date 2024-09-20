package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.challenges.ConnectorChallenge;
import eu.enmeshed.model.challenges.ConnectorChallengeValidationResult;
import eu.enmeshed.requests.challenges.CreateChallengeRequest;
import eu.enmeshed.requests.challenges.ValidateChallengeRequest;
import feign.Feign.Builder;
import feign.Headers;
import feign.RequestLine;

public interface ChallengesEndpoint {

  static ChallengesEndpoint configure(String url, Builder builder) {
    return builder.target(ChallengesEndpoint.class, url);
  }

  @RequestLine("POST /api/v2/Challenges")
  @Headers({"Accept: application/json", "Content-Type: application/json"})
  ConnectorResponse<ConnectorChallenge> createChallenge(CreateChallengeRequest request);

  @RequestLine("POST /api/v2/Challenges/Validate")
  @Headers({"Accept: application/json", "Content-Type: application/json"})
  ConnectorResponse<ConnectorChallengeValidationResult> validateChallenge(ValidateChallengeRequest request);
}
