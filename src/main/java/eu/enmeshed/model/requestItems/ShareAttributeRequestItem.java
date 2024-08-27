package eu.enmeshed.model.requestItems;

import eu.enmeshed.model.AttributeWrapper;
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
public class ShareAttributeRequestItem extends RequestItem {

  private Attribute attribute;

  private String sourceAttributeId;

  public static ShareAttributeRequestItem fromWrapper(
      AttributeWrapper wrapper, boolean mustBeAccepted) {

    return ShareAttributeRequestItem.builder()
        .mustBeAccepted(mustBeAccepted)
        .attribute(wrapper.getContent())
        .sourceAttributeId(wrapper.getId())
        .build();
  }
}
