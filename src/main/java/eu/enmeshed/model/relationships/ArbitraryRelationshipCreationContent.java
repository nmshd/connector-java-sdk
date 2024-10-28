package eu.enmeshed.model.relationships;

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
public final class ArbitraryRelationshipCreationContent
    extends RelationshipCreationContentDerivation {

  @JsonProperty("@type")
  private final String type = ArbitraryRelationshipCreationContent.class.getSimpleName();

  private Object value;
}
