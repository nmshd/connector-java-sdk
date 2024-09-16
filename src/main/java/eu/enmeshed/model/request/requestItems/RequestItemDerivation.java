package eu.enmeshed.model.request.requestItems;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
  @JsonSubTypes.Type(ReadAttributeRequestItem.class),
  @JsonSubTypes.Type(ShareAttributeRequestItem.class),
  @JsonSubTypes.Type(AuthenticationRequestItem.class),
  @JsonSubTypes.Type(ConsentRequestItem.class),
  @JsonSubTypes.Type(CreateAttributeRequestItem.class),
  @JsonSubTypes.Type(FreeTextRequestItem.class),
  @JsonSubTypes.Type(ProposeAttributeRequestItem.class)
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class RequestItemDerivation extends RequestItem {
  private Boolean requireManualDecision;
  private Boolean mustBeAccepted;
}
