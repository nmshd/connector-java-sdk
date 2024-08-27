package eu.enmeshed;

import static eu.enmeshed.model.ResultWrapper.containing;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import eu.enmeshed.client.EnmeshedClient;
import eu.enmeshed.model.request.LocalRequest;
import eu.enmeshed.model.request.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EnmeshedRequestServiceTest {
  private static final String TEST_REQUEST_ID = "testId";

  @Mock EnmeshedClient enmeshedClientMock;
  EnmeshedRequestService enmeshedRequestService;

  @BeforeEach
  void setup() {
    enmeshedRequestService = new EnmeshedRequestService(enmeshedClientMock);
  }

  @Test
  void shouldCorrectlyAcceptIncomingRequestById() {
    Request request = Request.builder().title("someTitle").build();
    LocalRequest expectedLocalRequest = LocalRequest.builder().peer("somePeer").build();
    when(enmeshedClientMock.acceptIncomingRequestById(eq(TEST_REQUEST_ID), any(Request.class)))
        .thenReturn(containing(expectedLocalRequest));

    LocalRequest actualLocalRequest =
        enmeshedRequestService.acceptIncomingRequestById(TEST_REQUEST_ID, request);

    assertSame(expectedLocalRequest, actualLocalRequest);
  }

  @Test
  void shouldCorrectlyGetIncomingRequestById() {
    LocalRequest expectedLocalRequest = LocalRequest.builder().peer("somePeer").build();
    when(enmeshedClientMock.getIncomingRequestById(eq(TEST_REQUEST_ID)))
        .thenReturn(containing(expectedLocalRequest));

    LocalRequest actualLocalRequest =
        enmeshedRequestService.getIncomingRequestById(TEST_REQUEST_ID);

    assertSame(expectedLocalRequest, actualLocalRequest);
  }
}
