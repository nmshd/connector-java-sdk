package eu.enmeshed.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.messages.MessageContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ResponseWrapper extends MessageContent {

  private String requestId;

  private String requestSourceReference;

  private RequestSourceType requestSourceType;

  private Response response;

  public enum RequestSourceType {
    @JsonProperty("Message")
    MESSAGE,

    @JsonProperty("RelationshipTemplate")
    RELATIONSHIP_TEMPLATE
  }
}
