package eu.enmeshed.requests.tokens;

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
public class GetOwnTokensQuery {

  private String createdAt;
  private String createdByDevice;
  private String expiresAt;
}
