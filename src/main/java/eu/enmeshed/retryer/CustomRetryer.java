package eu.enmeshed.retryer;

import static java.time.ZonedDateTime.now;

import feign.RetryableException;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomRetryer implements Retryer {
  private final int maxAttempts;
  private final long backoff;
  private int attempt;

  public CustomRetryer() {
    this(10000, 5);
  }

  public CustomRetryer(long backoff, int maxAttempts) {
    this.backoff = backoff;
    this.maxAttempts = maxAttempts;
    this.attempt = 1;
  }

  public void continueOrPropagate(RetryableException e) {
    var startTime = now().toInstant().toEpochMilli();
    log.info(
        "Retry, attempt number {}. Response status: {}. Start time: {}",
        attempt,
        e.status(),
        startTime);
    if (attempt++ >= maxAttempts) {
      log.error(
          "The maximum of retry attempts exceeded, attempt number: {}, time taken (millis) {}",
          attempt,
          now().toInstant().toEpochMilli() - startTime,
          e);
      throw e;
    }
    try {
      Thread.sleep(backoff);
    } catch (InterruptedException ignored) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public Retryer clone() {
    return new CustomRetryer(backoff, maxAttempts);
  }
}
