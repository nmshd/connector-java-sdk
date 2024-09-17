package eu.enmeshed.requests.incomingRequests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public sealed class DecideRequestItemDerivation extends DecideRequestItem permits RejectRequestItem, AcceptRequestItem {

  @JsonProperty("accept")
  private boolean accept;
}
