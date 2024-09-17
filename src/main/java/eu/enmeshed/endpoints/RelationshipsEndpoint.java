package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.attributes.ConnectorAttribute;
import eu.enmeshed.model.relationships.ConnectorRelationship;
import eu.enmeshed.requests.relationships.CreateRelationshipRequest;
import eu.enmeshed.requests.relationships.GetRelationshipsQuery;
import feign.Feign.Builder;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import java.util.List;

public interface RelationshipsEndpoint {

  static RelationshipsEndpoint configure(String url, Builder builder) {
    return builder.target(RelationshipsEndpoint.class, url);
  }

  @RequestLine("POST /api/v2/Relationships")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRelationship> createRelationship(CreateRelationshipRequest request);

  @RequestLine("GET /api/v2/Relationships")
  @Headers("Content-Type: application/json")
  ConnectorResponse<List<ConnectorRelationship>> getRelationships(@QueryMap GetRelationshipsQuery query);

  @RequestLine("GET /api/v2/Relationships/{relationshipId}")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRelationship> getRelationship(@Param("relationshipId") String relationshipId);

  @RequestLine("PUT /api/v2/Relationships/{relationshipId}/Accept")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRelationship> acceptRelationship(@Param("relationshipId") String relationshipId);

  @RequestLine("PUT /api/v2/Relationships/{relationshipId}/Reject")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRelationship> rejectRelationship(@Param("relationshipId") String relationshipId);

  @RequestLine("PUT /api/v2/Relationships/{relationshipId}/Revoke")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRelationship> revokeRelationship(@Param("relationshipId") String relationshipId);

  @RequestLine("GET /api/v2/Relationships/{relationshipId}/Attributes")
  @Headers("Content-Type: application/json")
  ConnectorResponse<List<ConnectorAttribute>> getAttributesForRelationship(@Param("relationshipId") String relationshipId);

  @RequestLine("PUT /api/v2/Relationships/{relationshipId}/Terminate")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRelationship> terminateRelationship(@Param("relationshipId") String relationshipId);

  @RequestLine("DELETE /api/v2/Relationships/{relationshipId}")
  @Headers("Content-Type: application/json")
  ConnectorResponse<Void> decomposeRelationship(@Param("relationshipId") String relationshipId);

  @RequestLine("PUT /api/v2/Relationships/{relationshipId}/Reactivate")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRelationship> requestRelationshipReactivation(@Param("relationshipId") String relationshipId);

  @RequestLine("PUT /api/v2/Relationships/{relationshipId}/Reactivate/Accept")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRelationship> acceptRelationshipReactivation(@Param("relationshipId") String relationshipId);

  @RequestLine("PUT /api/v2/Relationships/{relationshipId}/Reactivate/Reject")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRelationship> rejectRelationshipReactivation(@Param("relationshipId") String relationshipId);

  @RequestLine("PUT /api/v2/Relationships/{relationshipId}/Reactivate/Revoke")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorRelationship> revokeRelationshipReactivation(@Param("relationshipId") String relationshipId);
}
