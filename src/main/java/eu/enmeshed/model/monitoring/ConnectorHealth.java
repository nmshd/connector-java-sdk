package eu.enmeshed.model.monitoring;

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
public class ConnectorHealth {

  @JsonProperty("isHealthy")
  private boolean healthy;

  private Map<String, String> services;
}
