package eu.enmeshed.model.event;

import eu.enmeshed.model.request.LocalRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestStatusChangedEventData implements WebhookData {

  private LocalRequest request;

  private LocalRequest.LocalRequestStatus oldStatus;

  private LocalRequest.LocalRequestStatus newStatus;
}
