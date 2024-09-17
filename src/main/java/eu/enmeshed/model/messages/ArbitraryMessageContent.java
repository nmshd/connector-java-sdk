package eu.enmeshed.model.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ArbitraryMessageContent extends MessageContent {

  @JsonProperty("@type")
  private final String type = ArbitraryMessageContent.class.getSimpleName();

  private Object value;
}
