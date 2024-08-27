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
public class BirthPlace extends AttributeValue {

  private String city;

  private String country;

  private String state;
}
