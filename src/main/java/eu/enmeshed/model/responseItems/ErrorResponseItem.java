package eu.enmeshed.model.responseItems;

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
public class ErrorResponseItem extends ResponseItem {

  private final Result result = Result.ERROR;

  private String code;

  private String message;
}
