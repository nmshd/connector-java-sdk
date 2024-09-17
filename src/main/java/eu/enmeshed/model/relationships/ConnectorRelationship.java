package eu.enmeshed.model.relationships;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.account.ConnectorIdentityInfo;
import eu.enmeshed.model.event.WebhookData;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConnectorRelationship implements WebhookData {

  private String id;
  private RelationshipTemplate template;
  private RelationshipStatus status;
  private String peer;
  private ConnectorIdentityInfo peerIdentity;
  private RelationshipCreationContentDerivation creationContent;
  private List<RelationshipAuditLogEntry> auditLog;
  private PeerDeletionInfo peerDeletionInfo;

  @Data
  public static class PeerDeletionInfo {

    Status status;

    public enum Status {
      @JsonProperty("ToBeDeleted")
      TO_BE_DELETED,
      @JsonProperty("Deleted")
      DELETED
    }
  }
}
