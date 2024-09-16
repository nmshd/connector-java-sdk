package eu.enmeshed;

public record ConnectorError(
    String id,
    String code,
    String message,
    String docs,
    String time,
    String details,
    String[] stackTrace) {
  Exception toException() {
    return new Exception(message);
  }
}
