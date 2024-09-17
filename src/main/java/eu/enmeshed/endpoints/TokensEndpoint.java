package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.tokens.ConnectorToken;
import eu.enmeshed.requests.tokens.*;
import feign.Feign.Builder;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.nio.ByteBuffer;
import java.util.List;

public interface TokensEndpoint {

  static TokensEndpoint configure(String url, Builder builder) {
    return builder.target(TokensEndpoint.class, url);
  }

  @RequestLine("GET /api/v2/Tokens/{tokenId}")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorToken> getToken(@Param("tokenId") String tokenId);

  @RequestLine("GET /api/v2/Tokens/{tokenId}")
  @Headers("Accept: image/png")
  ConnectorResponse<ByteBuffer> getQrCodeForToken(@Param("tokenId") String tokenId);

  @RequestLine("GET /api/v2/Tokens/Own")
  @Headers("Content-Type: application/json")
  ConnectorResponse<List<ConnectorToken>> getOwnTokens(@QueryMap GetOwnTokensQuery query);

  @RequestLine("POST /api/v2/Tokens/Own")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorToken> createOwnToken(CreateOwnTokenRequest request);

  @RequestLine("GET /api/v2/Tokens/Peer")
  @Headers("Content-Type: application/json")
  ConnectorResponse<List<ConnectorToken>> getPeerTokens(@QueryMap GetPeerTokensQuery query);

  @RequestLine("POST /api/v2/Tokens/Peer")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorToken> loadPeerToken(LoadPeerTokenRequest request);
}