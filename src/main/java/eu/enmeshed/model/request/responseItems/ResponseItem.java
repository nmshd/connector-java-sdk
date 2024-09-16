package eu.enmeshed.model.request.responseItems;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
  @JsonSubTypes.Type(ResponseItemGroup.class),
  @JsonSubTypes.Type(ResponseItemDerivation.class),
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class ResponseItem {
  public enum Result {
    @JsonProperty("Accepted")
    ACCEPTED,

    @JsonProperty("Rejected")
    REJECTED,

    @JsonProperty("Error")
    ERROR
  }
}
