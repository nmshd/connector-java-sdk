package eu.enmeshed.model.relationshipTemplates;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.ContentWrapper;
import eu.enmeshed.model.ObjectReference;
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
public class RelationshipTemplate extends ContentWrapper<RelationshipTemplateContentDerivation>
    implements WebhookData {

  private String id;

  private Integer maxNumberOfAllocations;

  @JsonProperty("isOwn")
  private boolean own;

  private String createdBy;

  private String createdByDevice;

  private ZonedDateTime createdAt;

  private ZonedDateTime expiresAt;

  private String secretKey;

  private String truncatedReference;

  private ObjectReference reference;
}
