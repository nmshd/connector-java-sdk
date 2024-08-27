package eu.enmeshed.model.relationshipTemplates;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.requestItems.RequestItemGroup;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

  private ItemList onNewRelationship;

  @Builder
  public record ItemList(List<RequestItemGroup> items) {}
}
