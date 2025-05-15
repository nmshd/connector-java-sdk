package eu.enmeshed.requests.relationshipTemplates;

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
public class GetRelationshipTemplatesQuery {

  private String createdAt;
  private String expiresAt;
  private String createdBy;
  private String createdByDevice;
  private int maxNumberOfAllocations;
  @JsonProperty("isOwn")
  private boolean own;
}
