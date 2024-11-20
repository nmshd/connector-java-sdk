package eu.enmeshed.requests.attributes;

import eu.enmeshed.model.attributes.values.AttributeValue;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateRepositoryAttributeRequestContent {

  private AttributeValue value;
  private List<String> tags;
  private String validFrom;
  private String validTo;
}
