package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.qr.QrCode;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplate;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplateCreation;
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
  ConnectorResponse<RelationshipTemplate> createOwnRelationshipTemplate(
      RelationshipTemplateCreation relationshipTemplate);

  @RequestLine("GET /api/v2/RelationshipTemplates/{0}")
  @Headers("Accept: image/png")
  feign.Response getQrCodeForRelationshipTemplate(@Param("0") String relationshipTemplateId);

  @RequestLine("GET /api/v2/RelationshipTemplates/{relationshipTemplateId}")
  @Headers("Accept: application/json")
  ConnectorResponse<QrCode> createRelationshipQrCode(
      @Param("relationshipTemplateId") String relationshipTemplateId);
}
