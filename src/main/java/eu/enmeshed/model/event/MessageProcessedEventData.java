package eu.enmeshed.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.messaging.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageProcessedEventData implements WebhookData {

  private Message message;

  private Result result;

  public enum Result {
    @JsonProperty("ManualRequestDecisionRequired")
    MANUAL_REQUEST_DECISION_REQUIRED,

    @JsonProperty("NoRequest")
    NO_REQUEST,

    @JsonProperty("Error")
    ERROR
  }
}
