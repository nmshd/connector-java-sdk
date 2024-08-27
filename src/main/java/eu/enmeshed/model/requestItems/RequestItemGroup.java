package eu.enmeshed.model.requestItems;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

// @AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class RequestItemGroup extends RequestItem {

  private List<RequestItem> items;
}
