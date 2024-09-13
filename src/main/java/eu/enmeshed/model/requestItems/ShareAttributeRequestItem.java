package eu.enmeshed.model.requestItems;

import eu.enmeshed.model.AttributeWrapper;
import eu.enmeshed.model.attributes.Attribute;
import eu.enmeshed.model.attributes.IdentityAttribute;
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
public class ShareAttributeRequestItem extends RequestItem {
  private Attribute attribute;
  private String sourceAttributeId;

  public static ShareAttributeRequestItem fromWrapper(
      AttributeWrapper wrapper, boolean mustBeAccepted) {
    Attribute content = wrapper.getContent();
    content.setType(IdentityAttribute.class.getSimpleName());

    return ShareAttributeRequestItem.builder()
        .mustBeAccepted(mustBeAccepted)
        .attribute(content)
        .sourceAttributeId(wrapper.getId())
        .build();
  }
}
