package eu.enmeshed.exception.status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class EnmeshedHttpStatus {
  private final int errorCode;
  private final String message;
}
