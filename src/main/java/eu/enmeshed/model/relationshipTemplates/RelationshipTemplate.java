package eu.enmeshed.model.relationshipTemplates;

import eu.enmeshed.model.ContentWrapper;
import eu.enmeshed.model.event.WebhookData;
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
public class RelationshipTemplate extends ContentWrapper<RelationshipTemplateContent>
    implements WebhookData {

  private String id;

  private Integer maxNumberOfAllocations;

  private Boolean isOwn;

  private String createdBy;

  private String createdByDevice;

  private ZonedDateTime createdAt;

  private ZonedDateTime expiresAt;

  private String secretKey;

  private String truncatedReference;
}
