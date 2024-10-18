package eu.enmeshed.requests.outgoingRequests;

import feign.Param;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetOutgoingRequestsQuery {

  @Param("id")
  private String id;

  @Param("peer")
  private String peer;

  @Param("createdAt")
  private String createdAt;

  @Param("status")
  private String status;

  @Param("content.expiresAt")
  private String contentExpiresAt;

  @Param("content.items.@type")
  private String contentItemsType;

  @Param("source.type")
  private String sourceType;

  @Param("source.reference")
  private String sourceReference;

  @Param("response.createdAt")
  private String responseCreatedAt;

  @Param("response.source.type")
  private String responseSourceType;

  @Param("response.source.reference")
  private String responseSourceReference;

  @Param("response.content.result")
  private String responseContentResult;

  @Param("response.content.items.@type")
  private String responseContentItemsType;

  @Param("response.content.items.items.@type")
  private String responseContentItemsItemsType;
}
