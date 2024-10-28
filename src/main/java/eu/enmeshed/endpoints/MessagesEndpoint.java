package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.file.ConnectorFile;
import eu.enmeshed.model.messages.Message;
import eu.enmeshed.model.messages.MessageContent;
import eu.enmeshed.requests.messages.GetMessagesQuery;
import eu.enmeshed.requests.messages.SendMessageRequest;
import feign.Feign.Builder;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import java.util.List;

public interface MessagesEndpoint {

  static MessagesEndpoint configure(String url, Builder builder) {
    return builder.target(MessagesEndpoint.class, url);
  }

  @RequestLine("GET /api/v2/Messages")
  @Headers("Accept: application/json")
  ConnectorResponse<List<Message>> getMessages(@QueryMap GetMessagesQuery query);

  @RequestLine("POST /api/v2/Messages")
  @Headers("Accept: application/json")
  <T extends MessageContent> ConnectorResponse<Message> sendMessage(SendMessageRequest<T> request);

  @RequestLine("GET /api/v2/Messages/{messageId}")
  @Headers("Accept: application/json")
  ConnectorResponse<Message> getMessage(@Param("messageId") String messageId);

  @RequestLine("GET /api/v2/Messages/{messageId}/Attachments/{attachmentId}")
  @Headers("Accept: application/json")
  ConnectorResponse<ConnectorFile> getAttachment(@Param("messageId") String messageId, @Param("attachmentId") String attachmentId);

  @RequestLine("GET /api/v2/Messages/{messageId}/Attachments/{attachmentId}/Download")
  @Headers("Accept: application/json")
  feign.Response downloadAttachment(@Param("messageId") String messageId, @Param("attachmentId") String attachmentId);
}
