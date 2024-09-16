package eu.enmeshed.model;

import eu.enmeshed.model.attributes.Attribute;
import eu.enmeshed.model.attributes.AttributeShareInfo;
import java.time.ZonedDateTime;
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
public class AttributeWrapper extends ContentWrapper<Attribute> {

  private String id;
  private ZonedDateTime createdAt;
  private AttributeShareInfo shareInfo;
  private String succeededBy;
  private String succeeds;
  private Boolean isDefault;
}
