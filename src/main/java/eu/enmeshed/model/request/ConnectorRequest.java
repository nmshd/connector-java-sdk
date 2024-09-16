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
public class ConnectorRequest extends ContentWrapper<Request> implements WebhookData {

  private String id;
  private Boolean isOwn;
  private String peer;
  private ZonedDateTime createdAt;
  private Status status;
  private ConnectorRequestSource source;
  private ConnectorRequestResponse response;

  public enum Status {
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
