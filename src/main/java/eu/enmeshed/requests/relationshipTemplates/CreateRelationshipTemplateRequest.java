package eu.enmeshed.requests.relationshipTemplates;

import eu.enmeshed.model.relationshipTemplates.RelationshipTemplateContentDerivation;
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
public class CreateRelationshipTemplateRequest<T extends RelationshipTemplateContentDerivation> {

  private T content;
  private Integer maxNumberOfAllocations;
  private ZonedDateTime expiresAt;
}
