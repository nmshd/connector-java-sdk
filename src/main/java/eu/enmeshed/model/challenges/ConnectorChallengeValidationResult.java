package eu.enmeshed.model.challenges;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.relationships.ConnectorRelationship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConnectorChallengeValidationResult {

  @JsonProperty("isValid")
  private boolean valid;
  private ConnectorRelationship correspondingRelationship;
}
