package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.qr.QrCode;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplate;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplateContentDerivation;
import eu.enmeshed.requests.relationshipTemplates.CreateRelationshipTemplateRequest;
import eu.enmeshed.requests.relationshipTemplates.LoadPeerRelationshipTemplateRequest;
import feign.Feign.Builder;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface RelationshipTemplatesEndpoint {

  static RelationshipTemplatesEndpoint configure(String url, Builder builder) {
    return builder.target(RelationshipTemplatesEndpoint.class, url);
  }

  @RequestLine("POST /api/v2/RelationshipTemplates/Own")
  @Headers("Content-Type: application/json")
  <T extends RelationshipTemplateContentDerivation> ConnectorResponse<RelationshipTemplate> createOwnRelationshipTemplate(
      CreateRelationshipTemplateRequest<T> relationshipTemplate);

  @RequestLine("GET /api/v2/RelationshipTemplates/{relationshipTemplateId}")
  @Headers("Accept: image/png")
  feign.Response getQrCodeForRelationshipTemplate(@Param("relationshipTemplateId") String relationshipTemplateId);

  @RequestLine("GET /api/v2/RelationshipTemplates/{relationshipTemplateId}")
  @Headers("Accept: application/json")
  ConnectorResponse<QrCode> createRelationshipQrCode(@Param("relationshipTemplateId") String relationshipTemplateId);

  @RequestLine("POST /api/v2/RelationshipTemplates/Peer")
  @Headers({"Accept: application/json", "Content-Type: application/json"})
  ConnectorResponse<RelationshipTemplate> loadPeerRelationshipTemplate(LoadPeerRelationshipTemplateRequest request);
}
