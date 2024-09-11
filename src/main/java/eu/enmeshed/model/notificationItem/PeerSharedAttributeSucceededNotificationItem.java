package eu.enmeshed.model.notificationItem;

import eu.enmeshed.model.attributes.IdentityAttribute;
import eu.enmeshed.model.attributes.RelationshipAttribute;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class PeerSharedAttributeSucceededNotificationItem extends NotificationItem {
  private IdentityAttribute successorContent;
  private RelationshipAttribute attribute;
  private String predecessorId;
  private String successorId;
}
