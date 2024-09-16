package eu.enmeshed.model.request.responseItems;

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
public class ErrorResponseItem extends ResponseItemDerivation {

  private final Result result = Result.ERROR;
  private String code;
  private String message;
}
