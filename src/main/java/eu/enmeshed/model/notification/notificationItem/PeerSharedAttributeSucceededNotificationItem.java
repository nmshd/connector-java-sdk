package eu.enmeshed.model.notification.notificationItem;

import eu.enmeshed.model.attributes.Attribute;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PeerSharedAttributeSucceededNotificationItem extends NotificationItem {
  private Attribute successorContent;
  private String predecessorId;
  private String successorId;
}
