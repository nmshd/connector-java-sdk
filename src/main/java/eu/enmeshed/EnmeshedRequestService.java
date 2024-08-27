package eu.enmeshed;

import eu.enmeshed.client.EnmeshedClient;
import eu.enmeshed.model.request.LocalRequest;
import eu.enmeshed.model.request.Request;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class EnmeshedRequestService {
  private final EnmeshedClient enmeshedClient;

  public LocalRequest acceptIncomingRequestById(String requestId, Request request) {
    return enmeshedClient.acceptIncomingRequestById(requestId, request).getResult();
  }

  public LocalRequest getIncomingRequestById(String requestId) {
    return enmeshedClient.getIncomingRequestById(requestId).getResult();
  }
}
