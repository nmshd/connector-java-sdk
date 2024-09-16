package eu.enmeshed.model.monitoring;

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
public class ConnectorVersionInfo {
  private String version;
  private String build;
  private String date;
  private String commit;
  private String runtimeVersion;
}