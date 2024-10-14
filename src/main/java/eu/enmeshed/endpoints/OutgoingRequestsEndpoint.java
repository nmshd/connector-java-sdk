package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.request.ConnectorRequest;
import eu.enmeshed.model.request.ConnectorRequestValidationResult;
import eu.enmeshed.requests.outgoingRequests.CanCreateOutgoingRequestRequest;
import eu.enmeshed.requests.outgoingRequests.CreateOutgoingRequestRequest;
import eu.enmeshed.requests.outgoingRequests.GetOutgoingRequestsQuery;
import feign.Feign.Builder;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import java.util.List;

public interface OutgoingRequestsEndpoint {

  static OutgoingRequestsEndpoint configure(String url, Builder builder) {
    return builder.target(OutgoingRequestsEndpoint.class, url);
  }

  @RequestLine("POST /api/v2/Requests/Outgoing/Validate")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRequestValidationResult> canCreateRequest(CanCreateOutgoingRequestRequest request);

  @RequestLine("POST /api/v2/Requests/Outgoing")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRequest> createRequest(CreateOutgoingRequestRequest request);

  @RequestLine("GET /api/v2/Requests/Outgoing/{requestId}")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRequest> getRequest(@Param("requestId") String requestId);

  @RequestLine("GET /api/v2/Requests/Outgoing")
  @Headers("Content-Type: application/json")
  ConnectorResponse<List<ConnectorRequest>> getRequests(@QueryMap GetOutgoingRequestsQuery request);
}
