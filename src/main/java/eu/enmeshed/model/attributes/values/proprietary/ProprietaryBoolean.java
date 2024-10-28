package eu.enmeshed.model.attributes.values.proprietary;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.attributes.values.RelationshipAttributeValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ProprietaryBoolean extends RelationshipAttributeValue {

  private String title;
  private String description;

  @JsonProperty("value")
  private boolean value;
}
