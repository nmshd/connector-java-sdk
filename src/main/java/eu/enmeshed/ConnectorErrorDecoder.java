package eu.enmeshed;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.enmeshed.exceptions.PeerDeletionException;
import eu.enmeshed.exceptions.WrongRelationshipStatusException;
import feign.FeignException;
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

      FeignException feignException = FeignException.errorStatus(methodKey, response);

      if (connectorError.isRelationshipStatusWrong()) {
        return new WrongRelationshipStatusException(connectorError.message(), feignException.request(), responseBodyBytes, feignException.responseHeaders());
      }

      if (connectorError.hasPeerDeletionError()) {
        return new PeerDeletionException(connectorError.message(), feignException.request(), responseBodyBytes, feignException.responseHeaders());
      }

      return feignException;
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
