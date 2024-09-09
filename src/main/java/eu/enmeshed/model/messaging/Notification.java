package eu.enmeshed.model.messaging;

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
  private String id;
  private List<NotificationItem> items;
}
