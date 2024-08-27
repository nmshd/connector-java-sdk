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
public class ContentWrapper<T> {

  private T content;

  public static <T> ContentWrapper<T> containing(T content) {

    return new ContentWrapper<>(content);
  }
}
