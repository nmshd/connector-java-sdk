package eu.enmeshed.requests.relationships;

import eu.enmeshed.model.relationships.RelationshipCreationContentDerivation;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateRelationshipRequest {

  private String templateId;
  private RelationshipCreationContentDerivation creationContent;
}
