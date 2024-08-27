package eu.enmeshed.model.messaging;

import eu.enmeshed.model.ContentWrapper;
import eu.enmeshed.model.event.WebhookData;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Message extends ContentWrapper<MessageContent> implements WebhookData {

  private String id;

  private String createdBy;

  private String createdByDevice;

  private List<Recipient> recipients;

  private ZonedDateTime createdAt;

  private List<String> attachments;

  private Boolean isOwn;
}
