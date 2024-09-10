package eu.enmeshed.model.relationships;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
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
public class RelationshipAuditLog {
  private ZonedDateTime createdAt;
  private String createdBy;
  private String createdByDevice;
  private String Reason;
  private Status oldStatus;
  private Status newStatus;

  public enum Status {
    @JsonProperty("Pending")
    PENDING,
    @JsonProperty("Active")
    ACTIVE,
    @JsonProperty("Rejected")
    REJECTED
  }
}
