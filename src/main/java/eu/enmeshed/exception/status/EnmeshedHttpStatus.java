package eu.enmeshed.exception.status;

import lombok.Builder;

@Builder
public record EnmeshedHttpStatus(int errorCode, String message) {
}
