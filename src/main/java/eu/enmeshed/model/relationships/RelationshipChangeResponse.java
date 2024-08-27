package eu.enmeshed.model.relationships;

import eu.enmeshed.model.ContentWrapper;
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
public class RelationshipChangeResponse extends ContentWrapper<RelationshipChangeResponseContent> {

  private String createdBy;

  private String createdByDevice;

  private ZonedDateTime createdAt;
}
