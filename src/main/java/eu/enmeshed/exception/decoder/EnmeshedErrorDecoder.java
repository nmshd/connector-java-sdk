package eu.enmeshed.exception.decoder;

import feign.Request;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnmeshedErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    log.info("Response error with status {} and reason {}", response.status(), response.reason());

    int responseStatus = response.status();
    String responseReason = response.reason();
    log.info(
        "Throw the RetryableException from a response error with status {} and reason {}",
        responseStatus,
        responseReason);

    return new RetryableException(
        responseStatus,
        responseReason,
        Request.HttpMethod.valueOf(response.request().httpMethod().name()),
        null,
        1000L,
        response.request());
  }
}
