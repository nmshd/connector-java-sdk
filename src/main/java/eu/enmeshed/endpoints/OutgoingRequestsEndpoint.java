package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.request.ConnectorRequest;
import feign.Feign.Builder;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface OutgoingRequestsEndpoint {

  static OutgoingRequestsEndpoint configure(String url, Builder builder) {
    return builder.target(OutgoingRequestsEndpoint.class, url);
  }

  @RequestLine("POST /api/v2/Requests/Outgoing")
  @Headers("Accept: application/json")
  ConnectorResponse<ConnectorRequest> createOutgoingRequest(ConnectorRequest request);

  @RequestLine("GET /api/v2/Requests/Outgoing/{0}")
  ConnectorResponse<ConnectorRequest> getOutgoingRequest(@Param("0") String requestId);
}
