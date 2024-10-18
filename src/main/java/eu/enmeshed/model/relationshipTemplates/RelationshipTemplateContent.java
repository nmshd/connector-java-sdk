package eu.enmeshed.model.relationshipTemplates;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.request.Request;
import java.util.Map;
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
public final class RelationshipTemplateContent extends RelationshipTemplateContentDerivation {

  @JsonProperty("@type")
  private final String type = RelationshipTemplateContent.class.getSimpleName();

  private String title;
  private Request onNewRelationship;
  private Request onExistingRelationship;
  private Map<String, String> metadata;
}
