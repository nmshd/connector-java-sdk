package eu.enmeshed.requests.incomingRequests;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
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
public final class AcceptRequestItem extends DecideRequestItemDerivation {

  @JsonProperty("accept")
  private final boolean accept = true;
  private Map<String, Object> additionalProperties;
}
