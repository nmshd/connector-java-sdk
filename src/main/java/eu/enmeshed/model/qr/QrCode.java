package eu.enmeshed.model.qr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eu.enmeshed.model.relationshipTemplates.RelationshipTemplateContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QrCode {
  private String id;
  private String createdBy;
  private String createdByDevice;
  private String createdAt;
  private RelationshipTemplateContent content;
  private String expiresAt;
  private int maxNumberOfAllocations;
  private String truncatedReference;
}
