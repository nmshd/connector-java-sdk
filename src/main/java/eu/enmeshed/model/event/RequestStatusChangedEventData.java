package eu.enmeshed.model.event;

import eu.enmeshed.model.request.ConnectorRequest;
import eu.enmeshed.model.request.ConnectorRequest.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestStatusChangedEventData implements WebhookData {

  private ConnectorRequest request;

  private Status oldStatus;

  private Status newStatus;
}
