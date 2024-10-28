package eu.enmeshed.model.monitoring;

import eu.enmeshed.model.account.ConnectorIdentityInfo;
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
public class ConnectorSupportInformation {

  private ConnectorVersionInfo version;
  private ConnectorHealth health;
  private Map<String, Object> configuration;
  private ConnectorIdentityInfo identityInfo;
}
