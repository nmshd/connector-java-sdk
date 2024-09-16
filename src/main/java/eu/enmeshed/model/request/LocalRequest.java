package eu.enmeshed.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.ContentWrapper;
import eu.enmeshed.model.event.WebhookData;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class LocalRequest extends ContentWrapper<Request> implements WebhookData {

  private String id;
  private Boolean isOwn;
  private String peer;
  private ZonedDateTime createdAt;
  private LocalRequestStatus status;
  private LocalRequestSource source;
  private LocalRequestResponse response;

  public enum LocalRequestStatus {
    @JsonProperty("Draft")
    DRAFT,

    @JsonProperty("Open")
    OPEN,

    @JsonProperty("DecisionRequired")
    DECISION_REQUIRED,

    @JsonProperty("ManualDecisionRequired")
    MANUAL_DECISION_REQUIRED,

    @JsonProperty("Decided")
    DECIDED,

    @JsonProperty("Completed")
    COMPLETED,

    @JsonProperty("Expired")
    EXPIRED
  }
}
