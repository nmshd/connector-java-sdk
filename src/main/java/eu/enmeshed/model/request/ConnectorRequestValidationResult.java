package eu.enmeshed.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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
public class ConnectorRequestValidationResult {

  @JsonProperty("isSuccess")
  private boolean success;
  private String code;
  private String message;
  private List<ConnectorRequestValidationResult> items;
}
