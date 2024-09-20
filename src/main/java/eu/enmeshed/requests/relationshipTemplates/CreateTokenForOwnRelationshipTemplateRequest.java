package eu.enmeshed.requests.relationshipTemplates;

import java.time.ZonedDateTime;
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
public class CreateTokenForOwnRelationshipTemplateRequest {

  private ZonedDateTime expiresAt;
}
