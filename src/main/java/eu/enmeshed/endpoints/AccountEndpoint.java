package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.account.ConnectorIdentityInfo;
import eu.enmeshed.model.account.ConnectorSyncInfo;
import feign.Feign.Builder;
import feign.Headers;
import feign.RequestLine;

public interface AccountEndpoint {

  static AccountEndpoint configure(String url, Builder builder) {
    return builder.target(AccountEndpoint.class, url);
  }

  @RequestLine("GET /api/v2/Account/IdentityInfo")
  @Headers("Accept: application/json")
  ConnectorResponse<ConnectorIdentityInfo> getIdentityInfo();

  @RequestLine("POST /api/v2/Account/Sync")
  void sync();

  @RequestLine("GET /api/v2/Account/SyncInfo")
  @Headers("Accept: application/json")
  ConnectorResponse<ConnectorSyncInfo> getSyncInfo();
}
