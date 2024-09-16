package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.request.ConnectorRequest;
import eu.enmeshed.model.request.Request;
import feign.Feign.Builder;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface IncomingRequestsEndpoint {

  static IncomingRequestsEndpoint configure(String url, Builder builder) {
    return builder.target(IncomingRequestsEndpoint.class, url);
  }

  @RequestLine("GET /api/v2/Requests/Incoming/{requestId}")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRequest> getIncomingRequestById(@Param("requestId") String requestId);

  @RequestLine("PUT /api/v2/Requests/Incoming/{requestId}/Accept")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRequest> acceptIncomingRequestById(
      @Param("requestId") String requestId, Request request);
}
