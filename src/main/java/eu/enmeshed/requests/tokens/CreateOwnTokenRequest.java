package eu.enmeshed.requests.tokens;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
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
public class CreateOwnTokenRequest {

  private ZonedDateTime expiresAt;
  private Map<String, Object> content;
  @JsonProperty("ephemeral")
  private boolean ephemeral;
}
