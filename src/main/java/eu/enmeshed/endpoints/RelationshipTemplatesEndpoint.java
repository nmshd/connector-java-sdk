package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplate;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplateContentDerivation;
import eu.enmeshed.model.tokens.ConnectorToken;
import eu.enmeshed.requests.relationshipTemplates.CreateOwnRelationshipTemplateRequest;
import eu.enmeshed.requests.relationshipTemplates.CreateTokenForOwnRelationshipTemplateRequest;
import eu.enmeshed.requests.relationshipTemplates.CreateTokenQrCodeForOwnRelationshipTemplateRequest;
import eu.enmeshed.requests.relationshipTemplates.GetOwnRelationshipTemplatesQuery;
import eu.enmeshed.requests.relationshipTemplates.GetPeerRelationshipTemplatesQuery;
import eu.enmeshed.requests.relationshipTemplates.GetRelationshipTemplatesQuery;
import eu.enmeshed.requests.relationshipTemplates.LoadPeerRelationshipTemplateRequest;
import feign.Feign.Builder;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;
import java.util.List;

public interface RelationshipTemplatesEndpoint {

  static RelationshipTemplatesEndpoint configure(String url, Builder builder) {
    return builder.target(RelationshipTemplatesEndpoint.class, url);
  }

  @RequestLine("GET /api/v2/RelationshipTemplates")
  @Headers("Content-Type: application/json")
  ConnectorResponse<List<RelationshipTemplate>> getRelationshipTemplates(@QueryMap GetRelationshipTemplatesQuery request);

  @RequestLine("GET /api/v2/RelationshipTemplates/{id}")
  @Headers("Content-Type: application/json")
  ConnectorResponse<RelationshipTemplate> getRelationshipTemplate(@Param("id") String id);

  @RequestLine("GET /api/v2/RelationshipTemplates/Own")
  @Headers("Content-Type: application/json")
  ConnectorResponse<List<RelationshipTemplate>> getOwnRelationshipTemplates(@QueryMap GetOwnRelationshipTemplatesQuery request);

  @RequestLine("POST /api/v2/RelationshipTemplates/Own")
  @Headers("Content-Type: application/json")
  <T extends RelationshipTemplateContentDerivation> ConnectorResponse<RelationshipTemplate> createOwnRelationshipTemplate(CreateOwnRelationshipTemplateRequest<T> request);

  @RequestLine("GET /api/v2/RelationshipTemplates/{id}")
  @Headers("Accept: image/png")
  Response getQrCodeForOwnRelationshipTemplate(@Param("id") String id);

  @RequestLine("POST /api/v2/RelationshipTemplates/Own/{id}/Token")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorToken> createTokenForOwnRelationshipTemplate(@Param("id") String id, CreateTokenForOwnRelationshipTemplateRequest request);

  @RequestLine("POST /api/v2/RelationshipTemplates/Own/{id}/Token")
  @Headers("Accept: image/png")
  Response createTokenQrCodeForOwnRelationshipTemplate(@Param("id") String id, CreateTokenQrCodeForOwnRelationshipTemplateRequest request);

  @RequestLine("GET /api/v2/RelationshipTemplates/Peer")
  @Headers("Content-Type: application/json")
  ConnectorResponse<List<RelationshipTemplate>> getPeerRelationshipTemplates(@QueryMap GetPeerRelationshipTemplatesQuery request);

  @RequestLine("POST /api/v2/RelationshipTemplates/Peer")
  @Headers("Content-Type: application/json")
  ConnectorResponse<RelationshipTemplate> loadPeerRelationshipTemplate(LoadPeerRelationshipTemplateRequest request);
}
