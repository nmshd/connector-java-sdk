package eu.enmeshed.model.relationships;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class RelationshipChange {

  private String id;

  private Type type;

  private Status status;

  private RelationshipChangeRequest request;

  private RelationshipChangeResponse response;

  public enum Type {
    @JsonProperty("Creation")
    CREATION
  }

  public enum Status {
    @JsonProperty("Pending")
    PENDING,

    @JsonProperty("Rejected")
    REJECTED,

    @JsonProperty("Accepted")
    ACCEPTED
  }
}
