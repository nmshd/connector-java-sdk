package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.tokens.ConnectorToken;
import eu.enmeshed.requests.tokens.CreateOwnTokenRequest;
import eu.enmeshed.requests.tokens.GetOwnTokensQuery;
import eu.enmeshed.requests.tokens.GetPeerTokensQuery;
import eu.enmeshed.requests.tokens.LoadPeerTokenRequest;
import feign.Feign.Builder;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;
import java.util.List;

public interface TokensEndpoint {

  static TokensEndpoint configure(String url, Builder builder) {
    return builder.target(TokensEndpoint.class, url);
  }

  @RequestLine("GET /api/core/v1/Tokens/{tokenId}")
  @Headers("Accept: application/json")
  ConnectorResponse<ConnectorToken> getToken(@Param("tokenId") String tokenId);

  @RequestLine("GET /api/core/v1/Tokens/{tokenId}")
  @Headers("Accept: image/png")
  Response getQrCodeForToken(@Param("tokenId") String tokenId);

  @RequestLine("GET /api/core/v1/Tokens/Own")
  @Headers("Content-Type: application/json")
  ConnectorResponse<List<ConnectorToken>> getOwnTokens(@QueryMap GetOwnTokensQuery query);

  @RequestLine("POST /api/core/v1/Tokens/Own")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorToken> createOwnToken(CreateOwnTokenRequest request);

  @RequestLine("GET /api/core/v1/Tokens/Peer")
  @Headers("Content-Type: application/json")
  ConnectorResponse<List<ConnectorToken>> getPeerTokens(@QueryMap GetPeerTokensQuery query);

  @RequestLine("POST /api/core/v1/Tokens/Peer")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorToken> loadPeerToken(LoadPeerTokenRequest request);
}
