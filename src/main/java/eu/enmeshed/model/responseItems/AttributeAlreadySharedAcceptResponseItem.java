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
public class AttributeAlreadySharedAcceptResponseItem extends ResponseItem {

  private String attributeId;
  private ResponseItem.Result result;
}
