package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.relationships.Relationship;
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
  ConnectorResponse<List<Relationship>> searchRelationships(@Param("0") String templateId, @Param("1") String peer, @Param("2") String status);

  @RequestLine("GET /api/v2/Relationships/{id}")
  ConnectorResponse<Relationship> getRelationshipById(@Param("id") String id);

  @RequestLine("PUT /api/v2/Relationships/{id}/Accept")
  @Headers("Content-Type: application/json")
  ConnectorResponse<Relationship> acceptRelationship(@Param("id") String id);

  @RequestLine("PUT /api/v2/Relationships/{0}/Reject")
  @Headers("Content-Type: application/json")
  ConnectorResponse<Relationship> rejectRelationship(@Param("id") String id);
}
