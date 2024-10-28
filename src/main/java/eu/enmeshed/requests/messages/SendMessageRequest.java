package eu.enmeshed.requests.messages;

import eu.enmeshed.model.messages.MessageContent;
import java.util.List;
import lombok.Builder;

@Builder
public class SendMessageRequest<T extends MessageContent> {

  private T content;
  private List<String> recipients;
  private List<String> attachments;
}
