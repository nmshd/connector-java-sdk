package eu.enmeshed.model.request.responseItems;

import java.util.List;
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
public class ResponseItemGroup extends ResponseItem {

  private List<ResponseItemDerivation> items;
}
