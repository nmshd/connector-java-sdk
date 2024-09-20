package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.request.ConnectorRequest;
import eu.enmeshed.model.request.ConnectorRequestValidationResult;
import eu.enmeshed.requests.incomingRequests.DecideRequest;
import eu.enmeshed.requests.incomingRequests.GetIncomingRequestsQuery;
import feign.Feign.Builder;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import java.util.List;

public interface IncomingRequestsEndpoint {

  static IncomingRequestsEndpoint configure(String url, Builder builder) {
    return builder.target(IncomingRequestsEndpoint.class, url);
  }

  @RequestLine("PUT /api/v2/Requests/Incoming/{requestId}/CanAccept")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRequestValidationResult> canAccept(@Param("requestId") String requestId, DecideRequest request);

  @RequestLine("PUT /api/v2/Requests/Incoming/{requestId}/Accept")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRequest> accept(@Param("requestId") String requestId, DecideRequest request);

  @RequestLine("PUT /api/v2/Requests/Incoming/{requestId}/CanReject")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRequestValidationResult> canReject(@Param("requestId") String requestId, DecideRequest request);

  @RequestLine("PUT /api/v2/Requests/Incoming/{requestId}/Reject")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRequest> reject(@Param("requestId") String requestId, DecideRequest request);

  @RequestLine("GET /api/v2/Requests/Incoming/{requestId}")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRequest> getRequest(@Param("requestId") String requestId);

  @RequestLine("GET /api/v2/Requests/Incoming")
  @Headers("Content-Type: application/json")
  ConnectorResponse<List<ConnectorRequest>> getRequests(@QueryMap GetIncomingRequestsQuery request);
}
