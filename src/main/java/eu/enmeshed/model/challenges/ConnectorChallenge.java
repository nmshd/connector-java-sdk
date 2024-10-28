package eu.enmeshed.model.challenges;

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
public class ConnectorChallenge {
  private String id;
  private String expiresAt;
  private String createdBy;
  private String createdByDevice;
  private String type;
  private String signature;
  private String challengeString;
}
