package eu.enmeshed.model.attributes;

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
public class AttributeShareInfo {

  private String peer;

  private String requestReference;

  private String sourceAttribute;
}
