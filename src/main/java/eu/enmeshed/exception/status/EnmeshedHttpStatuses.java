package eu.enmeshed.exception.status;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static lombok.AccessLevel.PRIVATE;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class EnmeshedHttpStatuses {

  public static final EnmeshedHttpStatus ACCEPT_REQUEST_NO_RELATION_ID =
      EnmeshedHttpStatus.builder()
          .errorCode(HTTP_INTERNAL_ERROR)
          .message("Exception while getting relation Id from msg:")
          .build();
}
