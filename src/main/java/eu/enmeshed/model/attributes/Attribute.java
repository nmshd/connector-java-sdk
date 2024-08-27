package eu.enmeshed.model.attributes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.enmeshed.model.attributes.values.AttributeValue;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
  @JsonSubTypes.Type(IdentityAttribute.class),
  @JsonSubTypes.Type(RelationshipAttribute.class)
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class Attribute {

  private String owner;

  private List<String> tags;

  private ZonedDateTime validFrom;

  private ZonedDateTime validTo;

  private AttributeValue value;
}
