package eu.enmeshed.model.responseItems;

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
  // Group
  @JsonSubTypes.Type(ResponseItemGroup.class),

  // Accept Items
  @JsonSubTypes.Type(AcceptResponseItem.class),
  @JsonSubTypes.Type(ReadAttributeAcceptResponseItem.class),
  @JsonSubTypes.Type(ShareAttributeAcceptResponseItem.class),
  @JsonSubTypes.Type(CreateAttributeAcceptResponseItem.class),
  @JsonSubTypes.Type(ProposeAttributeAcceptResponseItem.class),
  @JsonSubTypes.Type(FreeTextAcceptResponseItem.class),
  @JsonSubTypes.Type(AttributeAlreadySharedAcceptResponseItem.class),

  // Reject Items
  @JsonSubTypes.Type(RejectResponseItem.class),

  // Error Items
  @JsonSubTypes.Type(ErrorResponseItem.class),
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class ResponseItem {

  private Result result;

  public enum Result {
    @JsonProperty("Accepted")
    ACCEPTED,

    @JsonProperty("Rejected")
    REJECTED,

    @JsonProperty("Error")
    ERROR
  }
}
