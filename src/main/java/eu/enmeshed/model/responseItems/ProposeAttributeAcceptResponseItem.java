package eu.enmeshed.model.responseItems;

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
public class ProposeAttributeAcceptResponseItem extends AcceptResponseItem {

  private String attributeId;

  private Attribute attribute;
}
