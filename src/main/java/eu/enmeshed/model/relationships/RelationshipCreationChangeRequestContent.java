package eu.enmeshed.model.relationships;

import eu.enmeshed.model.Response;
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
public class RelationshipCreationChangeRequestContent extends RelationshipChangeRequestContent {

  private Response response;
}
