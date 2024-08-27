package eu.enmeshed.model.requestItems;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
  @JsonSubTypes.Type(RequestItemGroup.class),
  @JsonSubTypes.Type(ReadAttributeRequestItem.class),
  @JsonSubTypes.Type(ShareAttributeRequestItem.class),
  @JsonSubTypes.Type(AuthenticationRequestItem.class),
  @JsonSubTypes.Type(ConsentRequestItem.class),
  @JsonSubTypes.Type(CreateAttributeRequestItem.class),
  @JsonSubTypes.Type(FreeTextRequestItem.class),
  @JsonSubTypes.Type(ProposeAttributeRequestItem.class),
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class RequestItem {

  private String title;

  private String description;

  private Map<String, String> metadata;

  private Boolean mustBeAccepted;

  private Boolean requireManualDecision;
}
