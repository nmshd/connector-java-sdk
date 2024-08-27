package eu.enmeshed.model.attributes.values.identity;

import eu.enmeshed.model.attributes.values.AttributeValue;
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
public class Affiliation extends AttributeValue {

  private String role;

  private String organization;

  private String unit;
}
