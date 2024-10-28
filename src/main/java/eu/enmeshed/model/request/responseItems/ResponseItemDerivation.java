package eu.enmeshed.model.request.responseItems;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
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
public class ResponseItemDerivation extends ResponseItem {

  private Result result;
}
