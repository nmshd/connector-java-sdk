package eu.enmeshed;

import java.util.Arrays;

public record ConnectorError(String id, String code, String message, String docs, String time, String details, String[] stacktrace) {

  public boolean isRelationshipStatusWrong() {
    return code.equals("error.consumption.requests.wrongRelationshipStatus");
  }

  public boolean hasPeerDeletionError() {
    return code.equals("error.consumption.requests.peerIsInDeletion") || code.equals("error.consumption.requests.peerIsDeleted");
  }

  @Override
  public String toString() {
    return "ConnectorError{" +
        "id='" + id + '\'' +
        ", code='" + code + '\'' +
        ", message='" + message + '\'' +
        ", docs='" + docs + '\'' +
        ", time='" + time + '\'' +
        ", details='" + details + '\'' +
        ", stackTrace=" + Arrays.toString(stacktrace) +
        '}';
  }
}
