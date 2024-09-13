package eu.enmeshed.exception.status;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public record EnmeshedHttpStatus(int errorCode, String message) {
}
