package eu.enmeshed.model.relationships;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RelationshipStaus {
  @JsonProperty("Pending")
  PENDING,
  @JsonProperty("Active")
  ACTIVE,
  @JsonProperty("Rejected")
  REJECTED,
  @JsonProperty("Revoked")
  REVOKED,
  @JsonProperty("Terminated")
  TERMINATED,
  @JsonProperty("DeletionProposed")
  DELETION_PROPOSED
}
