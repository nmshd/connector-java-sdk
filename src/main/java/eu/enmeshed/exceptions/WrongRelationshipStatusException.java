package eu.enmeshed.exceptions;

import feign.FeignException.BadRequest;
import feign.Request;
import java.util.Collection;
import java.util.Map;

public class WrongRelationshipStatusException extends BadRequest {

  public WrongRelationshipStatusException(String message, Request request, byte[] responseBodyByte, Map<String, Collection<String>> headers) {
    super(message, request, responseBodyByte, headers);
  }
}
