package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.relationships.ConnectorRelationship;
import eu.enmeshed.requests.relationships.CreateRelationshipRequest;
import feign.Feign.Builder;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface RelationshipsEndpoint {

  static RelationshipsEndpoint configure(String url, Builder builder) {
    return builder.target(RelationshipsEndpoint.class, url);
  }

  @RequestLine("GET /api/v2/Relationships?template.id={0}&peer={1}&status={2}")
  ConnectorResponse<List<ConnectorRelationship>> searchRelationships(
      @Param("0") String templateId, @Param("1") String peer, @Param("2") String status);

  @RequestLine("GET /api/v2/Relationships/{id}")
  ConnectorResponse<ConnectorRelationship> getRelationshipById(@Param("id") String id);

  @RequestLine("PUT /api/v2/Relationships/{id}/Accept")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRelationship> acceptRelationship(@Param("id") String id);

  @RequestLine("PUT /api/v2/Relationships/{0}/Reject")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRelationship> rejectRelationship(@Param("id") String id);

  @RequestLine("POST /api/v2/Relationships")
  @Headers({"Accept: application/json", "Content-Type: application/json"})
  ConnectorResponse<ConnectorRelationship> createRelationship(CreateRelationshipRequest request);
}
