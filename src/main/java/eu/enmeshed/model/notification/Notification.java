package eu.enmeshed.model.notification;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.messaging.MessageContent;
import eu.enmeshed.model.notificationItem.NotificationItem;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Notification extends MessageContent {
  @JsonProperty("@type")
  private String type;

  private String id;
  private List<NotificationItem> items;
}
