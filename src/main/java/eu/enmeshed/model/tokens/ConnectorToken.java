package eu.enmeshed.model.tokens;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.ObjectReference;
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
  @JsonProperty("isOwn")
  private boolean own;
  private String createdBy;
  private String createdByDevice;
  private Object content;
  private String createdAt;
  private String expiresAt;
  private String secretKey;
  private String truncatedReference;
  private ObjectReference reference;

  @JsonProperty("isEphemeral")
  private boolean ephemeral;
}
