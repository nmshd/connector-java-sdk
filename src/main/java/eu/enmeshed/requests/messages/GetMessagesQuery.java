package eu.enmeshed.requests.messages;

import feign.Param;
import lombok.Builder;

@Builder
public class GetMessagesQuery {
  private String createdBy;
  private String createdByDevice;
  private String createdAt;

  @Param("recipients.address")
  private String recipientsAddress;

  @Param("recipients.relationshipId")
  private String recipientsRelationshipId;

  private String participant;
  private String attachments;

  @Param("content.@type")
  private String contentType;

  @Param("content.subject")
  private String contentSubject;

  @Param("content.body")
  private String contentBody;
}
