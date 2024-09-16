package eu.enmeshed.model.monitoring;

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
public class ConnectorRequestCount {
  private String since;
  private int requestCount;
  private Map<String, Integer> requestCountByStatus;
}