package eu.enmeshed;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import eu.enmeshed.client.EnmeshedClient;
import eu.enmeshed.model.Response;
import eu.enmeshed.model.ResultWrapper;
import eu.enmeshed.model.messaging.Message;
import eu.enmeshed.model.messaging.SendMessage;
import eu.enmeshed.model.request.LocalRequest;
import eu.enmeshed.model.request.LocalRequestResponse;
import eu.enmeshed.model.request.LocalRequestSource;
import eu.enmeshed.model.request.Request;
import eu.enmeshed.model.request.RequestResponseSource;
import eu.enmeshed.model.request.requestItems.AuthenticationRequestItem;
import eu.enmeshed.model.request.responseItems.AcceptResponseItem;
import eu.enmeshed.model.request.responseItems.RejectResponseItem;
import feign.FeignException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class EnmeshedMessagingServiceTest {

  public static final String TEST_ENMESHED_ADDRESS = "idXXXXXXXXXXXXXXXXXXXXXXXXXXX";
  public static final String TEST_REQUEST_ID = "REQXXXXXXXXXXXXXXXXXXXXXXXXXX";
  public static final String TEST_MESSAGE_ID = "MSGXXXXXXXXXXXXXXXXXXXXXXXXXX";
  public static final String TEST_AUTH_TITLE = "Test Title";
  public static final String TEST_AUTH_TEXT = "Test Text";
  public static final Map<String, String> TEST_METADATA = Map.of("K1", "V1", "K2", "V2");
  @Mock EnmeshedClient enmeshedClientMock;
  EnmeshedMessagingService enmeshedMessagingService;

  @BeforeEach
  void setup() {

    enmeshedMessagingService = new EnmeshedMessagingService(enmeshedClientMock);
  }

  @Test
  void testSendMessage() throws EnmeshedMessagingService.SendMessageFailedException {

    ArgumentCaptor<LocalRequest> requestWrapperArgumentCaptor =
        ArgumentCaptor.forClass(LocalRequest.class);
    when(enmeshedClientMock.createOutgoingRequest(requestWrapperArgumentCaptor.capture()))
        .thenAnswer(
            invocationOnMock -> {
              LocalRequest passedRequest = invocationOnMock.getArgument(0, LocalRequest.class);
              return ResultWrapper.containing(
                  LocalRequest.builder()
                      .id(TEST_REQUEST_ID)
                      .isOwn(true)
                      .peer(TEST_ENMESHED_ADDRESS)
                      .createdAt(ZonedDateTime.now())
                      .status(LocalRequest.LocalRequestStatus.DRAFT)
                      .content(
                          Request.builder()
                              .id(TEST_REQUEST_ID)
                              .expiresAt(passedRequest.getContent().getExpiresAt())
                              .items(passedRequest.getContent().getItems())
                              .build())
                      .build());
            });

    ArgumentCaptor<SendMessage> sendMessageArgumentCaptor =
        ArgumentCaptor.forClass(SendMessage.class);
    when(enmeshedClientMock.sendMessage(sendMessageArgumentCaptor.capture()))
        .thenAnswer(
            invocationOnMock -> {
              SendMessage passedMessage = invocationOnMock.getArgument(0, SendMessage.class);
              return ResultWrapper.containing(
                  Message.builder()
                      .content(passedMessage.getContent())
                      .id(TEST_MESSAGE_ID)
                      .createdAt(ZonedDateTime.now())
                      .build());
            });

    String requestId =
        enmeshedMessagingService.sendAuthenticationRequest(
            TEST_ENMESHED_ADDRESS,
            TEST_AUTH_TITLE,
            TEST_AUTH_TEXT,
            true,
            Duration.of(24, ChronoUnit.HOURS),
            TEST_METADATA);

    // Test request is created before message is sent
    InOrder inOrder = Mockito.inOrder(enmeshedClientMock);
    inOrder.verify(enmeshedClientMock).createOutgoingRequest(any());
    inOrder.verify(enmeshedClientMock).sendMessage(any());

    // Test sent request
    Assertions.assertEquals(TEST_REQUEST_ID, requestId);
    Assertions.assertEquals(
        TEST_ENMESHED_ADDRESS, requestWrapperArgumentCaptor.getValue().getPeer());
    Assertions.assertNull(requestWrapperArgumentCaptor.getValue().getId());
    Assertions.assertEquals(
        1, requestWrapperArgumentCaptor.getValue().getContent().getItems().size());
    Assertions.assertInstanceOf(
        AuthenticationRequestItem.class,
        requestWrapperArgumentCaptor.getValue().getContent().getItems().get(0));
    Assertions.assertEquals(
        TEST_AUTH_TITLE,
        requestWrapperArgumentCaptor.getValue().getContent().getItems().get(0).getTitle());
    Assertions.assertEquals(
        TEST_AUTH_TEXT,
        requestWrapperArgumentCaptor.getValue().getContent().getItems().get(0).getDescription());
    Assertions.assertEquals(
        TEST_METADATA,
        requestWrapperArgumentCaptor.getValue().getContent().getItems().get(0).getMetadata());
    Assertions.assertTrue(
        requestWrapperArgumentCaptor
            .getValue()
            .getContent()
            .getItems()
            .get(0)
            .getRequireManualDecision());

    // Test sent message
    Assertions.assertNull(sendMessageArgumentCaptor.getValue().getAttachments());
    Assertions.assertEquals(1, sendMessageArgumentCaptor.getValue().getRecipients().size());
    Assertions.assertEquals(
        TEST_ENMESHED_ADDRESS, sendMessageArgumentCaptor.getValue().getRecipients().get(0));
    Assertions.assertInstanceOf(Request.class, sendMessageArgumentCaptor.getValue().getContent());
    Request sentRequest = (Request) sendMessageArgumentCaptor.getValue().getContent();
    Assertions.assertEquals(TEST_REQUEST_ID, sentRequest.getId());
    Assertions.assertEquals(1, sentRequest.getItems().size());
    Assertions.assertInstanceOf(AuthenticationRequestItem.class, sentRequest.getItems().get(0));
    Assertions.assertEquals(TEST_AUTH_TITLE, sentRequest.getItems().get(0).getTitle());
    Assertions.assertEquals(TEST_AUTH_TEXT, sentRequest.getItems().get(0).getDescription());
    Assertions.assertTrue(sentRequest.getItems().get(0).getRequireManualDecision());
  }

  @Test
  void testSendMessageWithoutMetadata() throws EnmeshedMessagingService.SendMessageFailedException {

    ArgumentCaptor<LocalRequest> requestWrapperArgumentCaptor =
        ArgumentCaptor.forClass(LocalRequest.class);
    when(enmeshedClientMock.createOutgoingRequest(requestWrapperArgumentCaptor.capture()))
        .thenAnswer(
            invocationOnMock -> {
              LocalRequest passedRequest = invocationOnMock.getArgument(0, LocalRequest.class);
              return ResultWrapper.containing(
                  LocalRequest.builder()
                      .id(TEST_REQUEST_ID)
                      .isOwn(true)
                      .peer(TEST_ENMESHED_ADDRESS)
                      .createdAt(ZonedDateTime.now())
                      .status(LocalRequest.LocalRequestStatus.DRAFT)
                      .content(
                          Request.builder()
                              .id(TEST_REQUEST_ID)
                              .expiresAt(passedRequest.getContent().getExpiresAt())
                              .items(passedRequest.getContent().getItems())
                              .build())
                      .build());
            });

    ArgumentCaptor<SendMessage> sendMessageArgumentCaptor =
        ArgumentCaptor.forClass(SendMessage.class);
    when(enmeshedClientMock.sendMessage(sendMessageArgumentCaptor.capture()))
        .thenAnswer(
            invocationOnMock -> {
              SendMessage passedMessage = invocationOnMock.getArgument(0, SendMessage.class);
              return ResultWrapper.containing(
                  Message.builder()
                      .content(passedMessage.getContent())
                      .id(TEST_MESSAGE_ID)
                      .createdAt(ZonedDateTime.now())
                      .build());
            });

    enmeshedMessagingService.sendAuthenticationRequest(
        TEST_ENMESHED_ADDRESS,
        TEST_AUTH_TITLE,
        TEST_AUTH_TEXT,
        true,
        Duration.of(24, ChronoUnit.HOURS));

    Assertions.assertEquals(
        Collections.emptyMap(),
        requestWrapperArgumentCaptor.getValue().getContent().getItems().get(0).getMetadata());
  }

  @ParameterizedTest
  @ValueSource(ints = {400, 404, 500})
  void testSendMessageFailsBecauseFailingRequest(int statusCode) {

    when(enmeshedClientMock.createOutgoingRequest(any()))
        .thenThrow(
            new FeignException.FeignClientException(
                statusCode,
                "",
                feign.Request.create(
                    feign.Request.HttpMethod.GET,
                    "",
                    Collections.emptyMap(),
                    new byte[0],
                    null,
                    null),
                new byte[0],
                null));

    EnmeshedMessagingService.SendMessageFailedException e =
        Assertions.assertThrows(
            EnmeshedMessagingService.SendMessageFailedException.class,
            () ->
                enmeshedMessagingService.sendAuthenticationRequest(
                    TEST_ENMESHED_ADDRESS,
                    TEST_AUTH_TITLE,
                    TEST_AUTH_TEXT,
                    true,
                    Duration.of(24, ChronoUnit.HOURS)));

    Assertions.assertEquals(statusCode, e.getHttpStatus());
    Assertions.assertEquals(TEST_AUTH_TITLE, e.getRequest().getTitle());
    Assertions.assertEquals(TEST_AUTH_TEXT, e.getRequest().getDescription());
    Assertions.assertNotNull(e.getReason());

    verify(enmeshedClientMock).createOutgoingRequest(any());
    verify(enmeshedClientMock, never()).sendMessage(any());
  }

  @Test
  void testResponseAccepted() {

    ZonedDateTime timestamp = ZonedDateTime.now();

    when(enmeshedClientMock.getOutgoingRequest(TEST_REQUEST_ID))
        .thenReturn(
            ResultWrapper.containing(
                LocalRequest.builder()
                    .id(TEST_REQUEST_ID)
                    .source(
                        LocalRequestSource.builder()
                            .reference(TEST_MESSAGE_ID)
                            .type(LocalRequestSource.RequestSourceType.MESSAGE)
                            .build())
                    .response(
                        LocalRequestResponse.builder()
                            .createdAt(timestamp)
                            .source(
                                RequestResponseSource.builder()
                                    .reference(TEST_MESSAGE_ID)
                                    .type(RequestResponseSource.RequestSourceType.MESSAGE)
                                    .build())
                            .content(
                                Response.builder()
                                    .requestId(TEST_REQUEST_ID)
                                    .result(Response.Result.ACCEPTED)
                                    .items(List.of(new AcceptResponseItem()))
                                    .build())
                            .build())
                    .content(
                        Request.builder()
                            .items(
                                List.of(
                                    AuthenticationRequestItem.builder()
                                        .requireManualDecision(true)
                                        .mustBeAccepted(true)
                                        .description(TEST_AUTH_TEXT)
                                        .title(TEST_AUTH_TITLE)
                                        .build()))
                            .build())
                    .status(LocalRequest.LocalRequestStatus.DECIDED)
                    .createdAt(timestamp)
                    .build()));

    EnmeshedMessagingService.AuthenticationStatus authenticationStatus =
        enmeshedMessagingService.getAuthenticationStatus(TEST_REQUEST_ID);

    verify(enmeshedClientMock).getOutgoingRequest(TEST_REQUEST_ID);

    Assertions.assertTrue(authenticationStatus.requestExists());
    Assertions.assertTrue(authenticationStatus.accepted());
    Assertions.assertFalse(authenticationStatus.rejected());
    Assertions.assertEquals(timestamp, authenticationStatus.respondedAt());
  }

  @Test
  void testResponseRejected() {

    ZonedDateTime timestamp = ZonedDateTime.now();

    when(enmeshedClientMock.getOutgoingRequest(TEST_REQUEST_ID))
        .thenReturn(
            ResultWrapper.containing(
                LocalRequest.builder()
                    .id(TEST_REQUEST_ID)
                    .source(
                        LocalRequestSource.builder()
                            .reference(TEST_MESSAGE_ID)
                            .type(LocalRequestSource.RequestSourceType.MESSAGE)
                            .build())
                    .response(
                        LocalRequestResponse.builder()
                            .createdAt(timestamp)
                            .source(
                                RequestResponseSource.builder()
                                    .reference(TEST_MESSAGE_ID)
                                    .type(RequestResponseSource.RequestSourceType.MESSAGE)
                                    .build())
                            .content(
                                Response.builder()
                                    .requestId(TEST_REQUEST_ID)
                                    .result(Response.Result.REJECTED)
                                    .items(List.of(new RejectResponseItem()))
                                    .build())
                            .build())
                    .content(
                        Request.builder()
                            .items(
                                List.of(
                                    AuthenticationRequestItem.builder()
                                        .requireManualDecision(true)
                                        .mustBeAccepted(true)
                                        .description(TEST_AUTH_TEXT)
                                        .title(TEST_AUTH_TITLE)
                                        .build()))
                            .build())
                    .status(LocalRequest.LocalRequestStatus.DECIDED)
                    .createdAt(timestamp)
                    .build()));

    EnmeshedMessagingService.AuthenticationStatus authenticationStatus =
        enmeshedMessagingService.getAuthenticationStatus(TEST_REQUEST_ID);

    verify(enmeshedClientMock).getOutgoingRequest(TEST_REQUEST_ID);

    Assertions.assertTrue(authenticationStatus.requestExists());
    Assertions.assertFalse(authenticationStatus.accepted());
    Assertions.assertTrue(authenticationStatus.rejected());
    Assertions.assertEquals(timestamp, authenticationStatus.respondedAt());
  }

  @Test
  void testResponseExpired() {

    ZonedDateTime timestamp = ZonedDateTime.now();

    when(enmeshedClientMock.getOutgoingRequest(TEST_REQUEST_ID))
        .thenReturn(
            ResultWrapper.containing(
                LocalRequest.builder()
                    .id(TEST_REQUEST_ID)
                    .source(
                        LocalRequestSource.builder()
                            .reference(TEST_MESSAGE_ID)
                            .type(LocalRequestSource.RequestSourceType.MESSAGE)
                            .build())
                    .response(
                        LocalRequestResponse.builder()
                            .createdAt(null)
                            .source(
                                RequestResponseSource.builder()
                                    .reference(TEST_MESSAGE_ID)
                                    .type(RequestResponseSource.RequestSourceType.MESSAGE)
                                    .build())
                            .content(null)
                            .build())
                    .content(
                        Request.builder()
                            .items(
                                List.of(
                                    AuthenticationRequestItem.builder()
                                        .requireManualDecision(true)
                                        .mustBeAccepted(true)
                                        .description(TEST_AUTH_TEXT)
                                        .title(TEST_AUTH_TITLE)
                                        .build()))
                            .build())
                    .status(LocalRequest.LocalRequestStatus.EXPIRED)
                    .createdAt(timestamp)
                    .build()));

    EnmeshedMessagingService.AuthenticationStatus authenticationStatus =
        enmeshedMessagingService.getAuthenticationStatus(TEST_REQUEST_ID);

    verify(enmeshedClientMock).getOutgoingRequest(TEST_REQUEST_ID);

    Assertions.assertTrue(authenticationStatus.requestExists());
    Assertions.assertFalse(authenticationStatus.accepted());
    Assertions.assertFalse(authenticationStatus.rejected());
    Assertions.assertTrue(authenticationStatus.expired());
    Assertions.assertNull(authenticationStatus.respondedAt());
  }

  @Test
  void testResponseNotDecided() {

    ZonedDateTime timestamp = ZonedDateTime.now();

    when(enmeshedClientMock.getOutgoingRequest(TEST_REQUEST_ID))
        .thenReturn(
            ResultWrapper.containing(
                LocalRequest.builder()
                    .id(TEST_REQUEST_ID)
                    .source(
                        LocalRequestSource.builder()
                            .reference(TEST_MESSAGE_ID)
                            .type(LocalRequestSource.RequestSourceType.MESSAGE)
                            .build())
                    .response(null)
                    .content(
                        Request.builder()
                            .items(
                                List.of(
                                    AuthenticationRequestItem.builder()
                                        .requireManualDecision(true)
                                        .mustBeAccepted(true)
                                        .description(TEST_AUTH_TEXT)
                                        .title(TEST_AUTH_TITLE)
                                        .build()))
                            .build())
                    .status(LocalRequest.LocalRequestStatus.DECISION_REQUIRED)
                    .createdAt(timestamp)
                    .build()));

    EnmeshedMessagingService.AuthenticationStatus authenticationStatus =
        enmeshedMessagingService.getAuthenticationStatus(TEST_REQUEST_ID);

    verify(enmeshedClientMock).getOutgoingRequest(TEST_REQUEST_ID);

    Assertions.assertTrue(authenticationStatus.requestExists());
    Assertions.assertFalse(authenticationStatus.accepted());
    Assertions.assertFalse(authenticationStatus.rejected());
    Assertions.assertNull(authenticationStatus.respondedAt());
  }

  @Test
  void testResponseRequestNotFound() {

    when(enmeshedClientMock.getOutgoingRequest(TEST_REQUEST_ID))
        .thenThrow(
            new FeignException.FeignClientException(
                404,
                "",
                feign.Request.create(
                    feign.Request.HttpMethod.GET,
                    "",
                    Collections.emptyMap(),
                    new byte[0],
                    null,
                    null),
                new byte[0],
                null));

    EnmeshedMessagingService.AuthenticationStatus authenticationStatus =
        enmeshedMessagingService.getAuthenticationStatus(TEST_REQUEST_ID);

    verify(enmeshedClientMock).getOutgoingRequest(TEST_REQUEST_ID);

    Assertions.assertFalse(authenticationStatus.requestExists());
    Assertions.assertFalse(authenticationStatus.accepted());
    Assertions.assertFalse(authenticationStatus.rejected());
    Assertions.assertNull(authenticationStatus.respondedAt());
  }

  @Test
  void testResponseUnexpectedError() {

    when(enmeshedClientMock.getOutgoingRequest(TEST_REQUEST_ID))
        .thenThrow(
            new FeignException.FeignClientException(
                500,
                "",
                feign.Request.create(
                    feign.Request.HttpMethod.GET,
                    "",
                    Collections.emptyMap(),
                    new byte[0],
                    null,
                    null),
                new byte[0],
                null));

    EnmeshedMessagingService.AuthenticationStatus authenticationStatus =
        enmeshedMessagingService.getAuthenticationStatus(TEST_REQUEST_ID);

    verify(enmeshedClientMock).getOutgoingRequest(TEST_REQUEST_ID);

    Assertions.assertNull(authenticationStatus);
  }
}
