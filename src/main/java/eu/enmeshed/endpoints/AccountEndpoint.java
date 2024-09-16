package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.IdentityInfo;
import feign.Feign.Builder;
import feign.RequestLine;

public interface AccountEndpoint {

  static AccountEndpoint configure(String url, Builder builder) {
    return builder.target(AccountEndpoint.class, url);
  }

  @RequestLine("GET /api/v2/Account/IdentityInfo")
  ConnectorResponse<IdentityInfo> getIdentityInfo();

  @RequestLine("POST /api/v2/Account/Sync")
  void sync();
}
