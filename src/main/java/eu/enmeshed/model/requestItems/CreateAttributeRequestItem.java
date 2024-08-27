package eu.enmeshed.model.requestItems;

import eu.enmeshed.model.AttributeWrapper;
import eu.enmeshed.model.attributes.Attribute;
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
public class CreateAttributeRequestItem extends RequestItem {

  private Attribute attribute;

  public static CreateAttributeRequestItem fromWrapper(
      AttributeWrapper wrapper, boolean mustBeAccepted) {
    return CreateAttributeRequestItem.builder()
        .attribute(relationshipAttribute(wrapper))
        .mustBeAccepted(mustBeAccepted)
        .build();
  }

  private static RelationshipAttribute relationshipAttribute(AttributeWrapper wrapper) {
    return RelationshipAttribute.builder()
        .owner(wrapper.getContent().getOwner())
        .key("AllowCertificateRequest")
        .confidentiality(RelationshipAttribute.Confidentiality.PRIVATE)
        .isTechnical(true)
        .value(ProprietaryBoolean.builder().title("Zeugnisanfragen erlauben").value(true).build())
        .build();
  }
}
