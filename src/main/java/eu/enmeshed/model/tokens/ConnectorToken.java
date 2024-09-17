package eu.enmeshed.model.tokens;

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
public class ConnectorToken {

  private String id;
  private String createdBy;
  private String createdByDevice;
  private Object content;
  private String createdAt;
  private String expiresAt;
  private String secretKey;
  private String truncatedReference;

  @JsonProperty("isEphemeral")
  private boolean ephemeral;
}
