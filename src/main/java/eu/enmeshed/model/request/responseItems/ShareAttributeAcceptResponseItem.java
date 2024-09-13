package eu.enmeshed.model.request.responseItems;

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
public class ShareAttributeAcceptResponseItem extends AcceptResponseItem {

  private String attributeId;

  private Result result;
}
