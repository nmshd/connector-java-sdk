package eu.enmeshed.model.request.responseItems;

import eu.enmeshed.model.attributes.Attribute;
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
public class ReadAttributeAcceptResponseItem extends AcceptResponseItem {

  private Attribute attribute;
  private String attributeId;
  private Result result;
}
