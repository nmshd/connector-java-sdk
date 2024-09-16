package eu.enmeshed.model.request.requestItems;

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
public class ConsentRequestItem extends RequestItemDerivation {

  private String consent;
  private String link;
}
