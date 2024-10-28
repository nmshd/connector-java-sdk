package eu.enmeshed.requests.incomingRequests;

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
public final class RejectRequestItem extends DecideRequestItemDerivation {

  private final boolean accept = false;
  private String code;
  private String message;
}
