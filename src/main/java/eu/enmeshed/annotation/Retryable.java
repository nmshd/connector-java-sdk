package eu.enmeshed.annotation;

import static java.util.Objects.nonNull;

import feign.RetryableException;
import feign.Retryer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import lombok.AllArgsConstructor;

@Retention(RetentionPolicy.RUNTIME)
public @interface Retryable {
  @AllArgsConstructor
  class AnnotationRetryer implements Retryer {
    private final Retryer delegate;

    @Override
    public void continueOrPropagate(RetryableException e) {
      if (isAnnotated(e)) delegate.continueOrPropagate(e);
      else throw e;
    }

    private boolean isAnnotated(RetryableException e) {
      return nonNull(
          e.request().requestTemplate().methodMetadata().method().getAnnotation(Retryable.class));
    }

    @Override
    public Retryer clone() {
      return new AnnotationRetryer(delegate.clone());
    }
  }
}
