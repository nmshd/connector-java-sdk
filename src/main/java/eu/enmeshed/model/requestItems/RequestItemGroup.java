package eu.enmeshed.model.requestItems;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RequestItemGroup {
  private List<RequestItem> items;
  private String title;
  private String description;
  private Map<String, String> metadata;
}
