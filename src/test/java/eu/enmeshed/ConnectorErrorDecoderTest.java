package eu.enmeshed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.enmeshed.exceptions.PeerDeletionException;
import eu.enmeshed.exceptions.WrongRelationshipStatusException;
import feign.FeignException;
import feign.FeignException.BadRequest;
import feign.Request;
import feign.Response;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class ConnectorErrorDecoderTest {

  private ConnectorErrorDecoder errorDecoder;
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
    errorDecoder = new ConnectorErrorDecoder(objectMapper); // Inject the mock
  }

  @Test
  void shouldThrowWrongRelationshipStatusException() throws IOException {
    String mockBody = "{\"error\": {\"message\": \"Relationship status is wrong\", \"code\": \"error.consumption.requests.wrongRelationshipStatus\"}}";

    Response response = createMockResponse(400, mockBody);
    FeignException exception = errorDecoder.decode("Post", response);

    assertInstanceOf(WrongRelationshipStatusException.class, exception);
    assertEquals("Relationship status is wrong", exception.getMessage());
  }

  @Test
  void shouldThrowPeerDeletionException() throws IOException {
    String mockBody = "{\"error\": {\"message\": \"Peer is in deletion\", \"code\": \"error.consumption.requests.peerIsInDeletion\"}}";

    Response response = createMockResponse(400, mockBody);
    FeignException exception = errorDecoder.decode("Post", response);

    assertInstanceOf(PeerDeletionException.class, exception);
    assertEquals("Peer is in deletion", exception.getMessage());
  }

  @Test
  void shouldThrowBadRequestException() throws IOException {
    String mockBody = "{\"error\": {\"message\": \"message\", \"code\": \"some other code\"}}";

    Response response = createMockResponse(400, mockBody);
    FeignException exception = errorDecoder.decode("Post", response);

    assertInstanceOf(BadRequest.class, exception);
  }

  // Helper method to create a mock Feign Response
  private Response createMockResponse(int status, String body) {
    Request request = Request.create(Request.HttpMethod.GET, "/test", Collections.emptyMap(), null, null, null);
    return Response.builder()
        .status(status)
        .reason("Some reason")
        .request(request)
        .body(body, StandardCharsets.UTF_8)
        .build();
  }
}