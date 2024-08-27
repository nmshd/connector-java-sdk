package eu.enmeshed.model.responseItems;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class AcceptResponseItem extends ResponseItem {

  private final Result result = Result.ACCEPTED;
}
