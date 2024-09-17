package eu.enmeshed.requests.outgoingRequests;

import eu.enmeshed.model.request.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateOutgoingRequestRequest {

  private Request content;
  private String peer;
}
