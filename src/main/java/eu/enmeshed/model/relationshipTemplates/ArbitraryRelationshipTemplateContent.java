package eu.enmeshed.model.relationshipTemplates;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public final class ArbitraryRelationshipTemplateContent
    extends RelationshipTemplateContentDerivation {

  @JsonProperty("@type")
  private final String type = ArbitraryRelationshipTemplateContent.class.getSimpleName();

  private Object value;
}
