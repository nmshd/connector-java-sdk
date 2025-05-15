package eu.enmeshed;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.enmeshed.exceptions.PeerDeletionException;
import eu.enmeshed.exceptions.WrongRelationshipStatusException;
import feign.FeignException;
import feign.FeignException.BadRequest;
import feign.FeignException.Conflict;
import feign.FeignException.FeignClientException;
import feign.FeignException.Forbidden;
import feign.FeignException.Gone;
import feign.FeignException.MethodNotAllowed;
import feign.FeignException.NotAcceptable;
import feign.FeignException.NotFound;
import feign.FeignException.TooManyRequests;
import feign.FeignException.Unauthorized;
import feign.FeignException.UnprocessableEntity;
import feign.FeignException.UnsupportedMediaType;
import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectorErrorDecoder implements ErrorDecoder {

  private final ObjectMapper objectMapper;

  public ConnectorErrorDecoder(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Override
  public FeignException decode(String methodKey, Response response) {
    log.info("Response error with status {} and reason {}", response.status(), response.reason());

    int responseStatus = response.status();
    String responseReason = response.reason();
    try (InputStream inputStream = response.body().asInputStream()) {

      byte[] responseBodyBytes = inputStream.readAllBytes();
      String responseBody = new String(responseBodyBytes);
      ConnectorError connectorError = objectMapper.convertValue(objectMapper.readTree(responseBody).get("error"), ConnectorError.class);

      var request = response.request();
      var headers = response.headers();
      var message = connectorError.message();

      if (connectorError.isRelationshipStatusWrong()) {
        return new WrongRelationshipStatusException(message, request, responseBodyBytes, headers);
      }

      if (connectorError.hasPeerDeletionError()) {
        return new PeerDeletionException(message, request, responseBodyBytes, headers);
      }

      return switch (responseStatus) {
        case 400 -> new BadRequest(message, request, responseBodyBytes, headers);
        case 401 -> new Unauthorized(message, request, responseBodyBytes, headers);
        case 403 -> new Forbidden(message, request, responseBodyBytes, headers);
        case 404 -> new NotFound(message, request, responseBodyBytes, headers);
        case 405 -> new MethodNotAllowed(message, request, responseBodyBytes, headers);
        case 406 -> new NotAcceptable(message, request, responseBodyBytes, headers);
        case 409 -> new Conflict(message, request, responseBodyBytes, headers);
        case 410 -> new Gone(message, request, responseBodyBytes, headers);
        case 415 -> new UnsupportedMediaType(message, request, responseBodyBytes, headers);
        case 429 -> new TooManyRequests(message, request, responseBodyBytes, headers);
        case 422 -> new UnprocessableEntity(message, request, responseBodyBytes, headers);
        default -> new FeignClientException(responseStatus, message, request, responseBodyBytes, headers);
      };
    } catch (IOException e) {
      log.error("Failed to parse error response body", e);
      return new RetryableException(
          responseStatus,
          responseReason,
          Request.HttpMethod.valueOf(response.request().httpMethod().name()),
          e,
          1000L,
          response.request());
    }
  }
}
