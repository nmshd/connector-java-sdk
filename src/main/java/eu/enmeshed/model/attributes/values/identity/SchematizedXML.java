package eu.enmeshed.model.attributes.values.identity;

import eu.enmeshed.model.attributes.values.SimpleStringAttributeValue;
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
public class SchematizedXML extends SimpleStringAttributeValue {

  private String schemaURL;
}
