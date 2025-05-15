package eu.enmeshed.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.enmeshed.model.messages.MessageContent;
import eu.enmeshed.model.request.requestItems.RequestItem;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@type",
    visible = true,
    defaultImpl = Request.class
)
public class Request extends MessageContent {
  @JsonProperty("@type")
  @Builder.Default
  private String type = "Request";
  private String id;
  private String title;
  private String description;
  private ZonedDateTime expiresAt;
  private List<RequestItem> items;
  private String metadata;
}
