package eu.enmeshed.model.notification.notificationItem;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({@JsonSubTypes.Type(PeerSharedAttributeSucceededNotificationItem.class)})
@Getter
@Setter
@SuperBuilder
public abstract class NotificationItem {}
