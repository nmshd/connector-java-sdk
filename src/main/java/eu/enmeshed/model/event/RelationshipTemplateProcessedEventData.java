package eu.enmeshed.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelationshipTemplateProcessedEventData implements WebhookData {

  private RelationshipTemplate template;

  private Result result;

  public enum Result {
    @JsonProperty("ManualRequestDecisionRequired")
    MANUAL_REQUEST_DECISION_REQUIRED,

    @JsonProperty("NonCompletedRequestExists")
    NON_COMPLETED_REQUEST_EXISTS,

    @JsonProperty("RelationshipExists")
    RELATIONSHIP_EXISTS,

    @JsonProperty("NoRequest")
    NO_REQUEST,

    @JsonProperty("Error")
    ERROR
  }
}
