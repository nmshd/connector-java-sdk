package eu.enmeshed.model.relationshipTemplates;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.request.Request;
import java.util.Map;
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
public class RelationshipTemplateContent {
  @JsonProperty("@type")
  private final String type = RelationshipTemplateContent.class.getSimpleName();

  private String title;
  private Request onNewRelationship;
  private Request onExistingRelationship;
  private Map<String, String> metadata;
}
