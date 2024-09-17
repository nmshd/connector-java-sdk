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

  public static CreateAttributeRequestItem fromWrapper(ConnectorAttribute wrapper, boolean mustBeAccepted) {
    return CreateAttributeRequestItem.builder().attribute(relationshipAttribute(wrapper)).mustBeAccepted(mustBeAccepted).build();
  }

  private static RelationshipAttribute relationshipAttribute(ConnectorAttribute wrapper) {
    return RelationshipAttribute.builder().type(RelationshipAttribute.class.getSimpleName()).owner(wrapper.getContent().getOwner()).key("AllowCertificateRequest")
        .confidentiality(RelationshipAttribute.Confidentiality.PRIVATE).technical(true).value(ProprietaryBoolean.builder().title("Zeugnisanfragen erlauben").value(true).build())
        .build();
  }
}
