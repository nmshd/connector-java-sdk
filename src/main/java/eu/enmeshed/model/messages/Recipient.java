package eu.enmeshed.model.messages;

import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Recipient {

  private String address;

  private String relationshipId;

  private ZonedDateTime receivedAt;

  private String receivedByDevice;
}
