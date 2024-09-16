package eu.enmeshed.model.messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.enmeshed.model.notification.Notification;
import eu.enmeshed.model.request.Request;
import eu.enmeshed.model.request.ResponseWrapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
  @JsonSubTypes.Type(Mail.class),
  @JsonSubTypes.Type(Request.class),
  @JsonSubTypes.Type(ResponseWrapper.class),
  @JsonSubTypes.Type(Notification.class)
})
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class MessageContent {}
