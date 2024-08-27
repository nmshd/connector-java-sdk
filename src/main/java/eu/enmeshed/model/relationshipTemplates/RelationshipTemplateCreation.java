package eu.enmeshed.model.relationshipTemplates;

import eu.enmeshed.model.ContentWrapper;
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
public class RelationshipTemplateCreation extends ContentWrapper<RelationshipTemplateContent> {

  private Integer maxNumberOfAllocations;

  private ZonedDateTime expiresAt;
}
