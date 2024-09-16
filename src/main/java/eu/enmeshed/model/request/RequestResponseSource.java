package eu.enmeshed.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class RequestResponseSource {

  private RequestSourceType type;
  private String reference;

  public enum RequestSourceType {
    @JsonProperty("Message")
    MESSAGE,

    @JsonProperty("RelationshipTemplate")
    RELATIONSHIP_TEMPLATE
  }
}
