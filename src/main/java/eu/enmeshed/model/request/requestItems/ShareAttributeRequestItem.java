package eu.enmeshed.model.request.requestItems;

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
public class ShareAttributeRequestItem extends RequestItemDerivation {

  private Attribute attribute;
  private String sourceAttributeId;
}
