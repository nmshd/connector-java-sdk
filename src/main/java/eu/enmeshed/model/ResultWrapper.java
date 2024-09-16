package eu.enmeshed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ResultWrapper<T> {

  private T result;

  public static <T> ResultWrapper<T> containing(T content) {
    return new ResultWrapper<>(content);
  }
}
