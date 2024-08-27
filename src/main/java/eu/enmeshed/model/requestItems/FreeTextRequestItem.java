package eu.enmeshed.model.requestItems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class FreeTextRequestItem extends RequestItem {
  Boolean accept;
  String freeText;
}
