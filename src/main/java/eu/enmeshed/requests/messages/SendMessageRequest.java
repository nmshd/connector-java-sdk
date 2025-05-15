package eu.enmeshed.requests.messages;

import eu.enmeshed.model.messages.MessageContent;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SendMessageRequest<T extends MessageContent> {

  private T content;
  private List<String> recipients;
  private List<String> attachments;
}
