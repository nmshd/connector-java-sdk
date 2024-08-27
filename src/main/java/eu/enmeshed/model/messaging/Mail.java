package eu.enmeshed.model.messaging;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Mail extends MessageContent {

  private List<String> to;

  private List<String> cc;

  private String subject;

  private String body;
}
