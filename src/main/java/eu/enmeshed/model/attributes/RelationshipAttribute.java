package eu.enmeshed.model.attributes;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class RelationshipAttribute extends Attribute {

  private Confidentiality confidentiality;

  private Boolean isTechnical;

  private String key;

  public enum Confidentiality {
    @JsonProperty("public")
    PUBLIC,

    @JsonProperty("private")
    PRIVATE,

    @JsonProperty("protected")
    PROTECTED
  }
}
