package eu.enmeshed.model.request.requestItems;

import eu.enmeshed.model.attributes.Attribute;
import eu.enmeshed.model.attributes.ConnectorAttribute;
import eu.enmeshed.model.attributes.RelationshipAttribute;
import eu.enmeshed.model.attributes.values.proprietary.ProprietaryBoolean;
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
public class CreateAttributeRequestItem extends RequestItemDerivation {

  private Attribute attribute;
}
