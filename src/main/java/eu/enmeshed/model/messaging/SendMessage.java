package eu.enmeshed.model.messaging;

import eu.enmeshed.model.ContentWrapper;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class SendMessage extends ContentWrapper<MessageContent> {

  private List<String> recipients;

  private List<String> attachments;
}
