package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.attributes.ConnectorAttribute;
import eu.enmeshed.requests.attributes.CreateRepositoryAttributeRequest;
import eu.enmeshed.requests.attributes.SucceedAttributeRequest;
import feign.Feign.Builder;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface AttributesEndpoint {

  static AttributesEndpoint configure(String url, Builder builder) {
    return builder.target(AttributesEndpoint.class, url);
  }

  @RequestLine("POST /api/v2/Attributes")
  @Headers({"Content-Type: application/json"})
  ConnectorResponse<ConnectorAttribute> createRepositoryAttribute(CreateRepositoryAttributeRequest request);

  @RequestLine("POST /api/v2/Attributes/{id}/Succeed")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorAttribute> succeedAttribute(@Param("id") String predecessorId, SucceedAttributeRequest request);

  @RequestLine("GET /api/v2/Attributes?content.value.@type={type}")
  ConnectorResponse<List<ConnectorAttribute>> searchAttributes(@Param("type") String contentValueType);

  @RequestLine("GET /api/v2/Attributes/{id}")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorAttribute> getAttributeById(@Param("id") String attributeId);
}
