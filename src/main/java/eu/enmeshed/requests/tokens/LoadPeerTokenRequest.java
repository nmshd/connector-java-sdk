package eu.enmeshed.requests.tokens;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class LoadPeerTokenRequest {

  private String reference;
  @JsonProperty("ephemeral")
  private boolean ephemeral;
}
