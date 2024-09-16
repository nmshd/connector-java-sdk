package eu.enmeshed.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.request.responseItems.ResponseItem;
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
public class Response {
  private List<ResponseItem> items;
  private String requestId;
  private Result result;

  public enum Result {
    @JsonProperty("Accepted")
    ACCEPTED,

    @JsonProperty("Rejected")
    REJECTED,
  }
}
