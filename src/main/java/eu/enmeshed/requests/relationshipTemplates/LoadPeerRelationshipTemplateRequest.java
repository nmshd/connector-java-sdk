package eu.enmeshed.requests.relationshipTemplates;

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
public class LoadPeerRelationshipTemplateRequest {

  private String reference;
}
