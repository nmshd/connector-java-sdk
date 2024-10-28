package eu.enmeshed;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectorErrorDecoder implements ErrorDecoder {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public Exception decode(String methodKey, Response response) {
    log.info("Response error with status {} and reason {}", response.status(), response.reason());

    int responseStatus = response.status();
    String responseReason = response.reason();
    log.info(
        "Throw the RetryableException from a response error with status {} and reason {}",
        responseStatus,
        responseReason);

    try (InputStream bodyIs = response.body().asInputStream()) {
      ConnectorErrorWrapper wrapper = objectMapper.readValue(bodyIs, ConnectorErrorWrapper.class);
      var error = wrapper.error();
      log.info(error.toString());

      return error.toException();
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
