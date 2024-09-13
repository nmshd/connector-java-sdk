package eu.enmeshed;

import eu.enmeshed.client.EnmeshedClient;
import eu.enmeshed.model.Response;
import eu.enmeshed.model.messaging.SendMessage;
import eu.enmeshed.model.request.LocalRequest;
import eu.enmeshed.model.request.Request;
import eu.enmeshed.model.requestItems.AuthenticationRequestItem;
import feign.FeignException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class EnmeshedMessagingService {

  private final EnmeshedClient enmeshedClient;

  /**
   * Send an authentication request to an enmeshed wallet. The peer needs to be already onboarded in
   * order to send a message. The receiver can accept or reject the authentication request. The
   * decision of the user can be checked with getAuthenticationStatus(String requestId).
   *
   * @param receiver enmeshed address of the receiver
   * @param displayTitle Displayed title of the authentication request
   * @param displayText Displayed description of the authentication request
   * @param mandatory flag whether the acceptance of this authentication request is displayed as
   *     mandatory.
   * @param lifetime Lifetime of the request, how long the receiver can answer the authentication
   *     request.
   * @return the Request-ID to request the answer of the authentication request
   */
  public String sendAuthenticationRequest(
      String receiver,
      String displayTitle,
      String displayText,
      boolean mandatory,
      Duration lifetime)
      throws SendMessageFailedException {

    return sendAuthenticationRequest(
        receiver, displayTitle, displayText, mandatory, lifetime, Collections.emptyMap());
  }

  /**
   * Send an authentication request to an enmeshed wallet. The peer needs to be already onboarded in
   * order to send a message. The receiver can accept or reject the authentication request. The
   * decision of the user can be checked with getAuthenticationStatus(String requestId).
   *
   * @param receiver enmeshed address of the receiver
   * @param displayTitle Displayed title of the authentication request
   * @param displayText Displayed description of the authentication request
   * @param mandatory flag whether the acceptance of this authentication request is displayed as
   *     mandatory.
   * @param lifetime Lifetime of the request, how long the receiver can answer the authentication
   *     request.
   * @param metadata Map with metadata that will be attached to the AuthenticationRequestItem.
   * @return the Request-ID to request the answer of the authentication request
   */
  public String sendAuthenticationRequest(
      String receiver,
      String displayTitle,
      String displayText,
      boolean mandatory,
      Duration lifetime,
      Map<String, String> metadata)
      throws SendMessageFailedException {

    Request request =
        Request.builder()
            .title(displayTitle)
            .description(displayText)
            .expiresAt(ZonedDateTime.now().plus(lifetime))
            .items(
                List.of(
                    AuthenticationRequestItem.builder()
                        .title(displayTitle)
                        .description(displayText)
                        .mustBeAccepted(mandatory)
                        .requireManualDecision(true)
                        .metadata(metadata)
                        .build()))
            .build();

    try {
      Request createdRequest =
          enmeshedClient
              .createOutgoingRequest(LocalRequest.builder().peer(receiver).content(request).build())
              .getResult()
              .getContent();

      enmeshedClient.sendMessage(
          SendMessage.builder().recipients(List.of(receiver)).content(createdRequest).build());

      return createdRequest.getId();
    } catch (FeignException e) {
      if (e.status() == 404) {
        throw new SendMessageFailedException(
            request, 404, "Receiver doesn't exist or is not properly onboarded.");
      } else if (e.status() == 400) {
        throw new SendMessageFailedException(request, 400, "Malformed message content.");
      } else {
        throw new SendMessageFailedException(
            request,
            e.status(),
            "Unexpected exception occurred when sending message: " + e.getMessage());
      }
    }
  }

  /**
   * Retrieve the Status of an AuthenticationRequest.
   *
   * @param requestId ID of the request returned previously by sendAuthenticationRequest()
   * @return {@link AuthenticationStatus} containing information if the request exists, the receiver
   *     has answered and how he has decided. Returns null if the request does not exist.
   */
  public AuthenticationStatus getAuthenticationStatus(String requestId) {

    enmeshedClient.sync();

    LocalRequest localRequest;
    try {
      localRequest = enmeshedClient.getOutgoingRequest(requestId).getResult();
    } catch (FeignException e) {
      if (e.status() == 404) {
        return new AuthenticationStatus(null, false, false, false, false);
      } else {
        return null;
      }
    }

    if (localRequest.getStatus() == LocalRequest.LocalRequestStatus.EXPIRED) {
      return new AuthenticationStatus(null, true, false, false, true);
    } else if (localRequest.getResponse() != null) {
      return new AuthenticationStatus(
          localRequest.getResponse().getCreatedAt(),
          true,
          localRequest.getResponse().getContent().getResult() == Response.Result.ACCEPTED,
          localRequest.getResponse().getContent().getResult() == Response.Result.REJECTED,
          false);
    } else {
      return new AuthenticationStatus(null, true, false, false, false);
    }
  }

  @RequiredArgsConstructor
  @Getter
  public static class SendMessageFailedException extends Exception {

    private final Request request;
    private final int httpStatus;
    private final String Reason;
  }

  public record AuthenticationStatus(
      ZonedDateTime respondedAt,
      boolean requestExists,
      boolean accepted,
      boolean rejected,
      boolean expired) {}
}
