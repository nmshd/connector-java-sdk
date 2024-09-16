package eu.enmeshed.model.attributes.values;

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
public abstract class SimpleStringAttributeValue extends AttributeValue {

  private String value;

  @Override
  public String toString() {
    return getValue();
  }
}
