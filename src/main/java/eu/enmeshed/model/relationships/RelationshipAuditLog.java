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
    private Reason reason;
    private RelationshipStaus oldStatus;
    private RelationshipStaus newStatus;

    public enum Reason {
        @JsonProperty("Creation")
        CREATION,
        @JsonProperty("AcceptanceOfCreation")
        ACCEPTANCE_OF_CREATION,
        @JsonProperty("RejectionOfCreation")
        REJECTION_OF_CREATION,
        @JsonProperty("RevocationOfCreation")
        REVOCATION_OF_CREATION,
        @JsonProperty("Termination")
        TERMINATION,
        @JsonProperty("ReactivationRequested")
        REACTIVATION_REQUESTED,
        @JsonProperty("AcceptanceOfReactivation")
        ACCEPTANCE_OF_REACTIVATION,
        @JsonProperty("RejectionOfReactivation")
        REJECTION_OF_REACTIVATION,
        @JsonProperty("RevocationOfReactivation")
        REVOCATION_OF_REACTIVATION,
        @JsonProperty("Decomposition")
        DECOMPOSITION
    }
}
