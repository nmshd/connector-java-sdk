package eu.enmeshed.exception;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;

import eu.enmeshed.exception.status.EnmeshedHttpStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EnmeshedException extends RuntimeException {

  private int errorCode;

  public EnmeshedException(String message) {
    super(message);
    this.errorCode = HTTP_INTERNAL_ERROR;
  }

  public EnmeshedException(EnmeshedHttpStatus httpStatus) {
    super(httpStatus.getMessage());
    this.errorCode = httpStatus.getErrorCode();
  }
}
