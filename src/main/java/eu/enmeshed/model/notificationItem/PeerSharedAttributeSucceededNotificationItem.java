package eu.enmeshed.model.notificationItem;

import eu.enmeshed.model.attributes.IdentityAttribute;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PeerSharedAttributeSucceededNotificationItem extends NotificationItem {
  private IdentityAttribute successorContent;
}
