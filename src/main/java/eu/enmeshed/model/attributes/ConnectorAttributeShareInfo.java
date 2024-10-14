package eu.enmeshed.model.attributes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ConnectorAttributeShareInfo {

  private String peer;
  private String requestReference;
  private String notificationReference;
  private String sourceAttribute;
}
