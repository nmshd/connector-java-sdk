package eu.enmeshed.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eu.enmeshed.model.messages.MessageContent;
import eu.enmeshed.model.request.requestItems.RequestItem;
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
@JsonIgnoreProperties({"@type"})
public class Request extends MessageContent {

  private String id;
  private String title;
  private String description;
  private ZonedDateTime expiresAt;
  private List<RequestItem> items;
  private String metadata;
}
