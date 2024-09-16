package eu.enmeshed.model.file;

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
public class ConnectorFile {

  private String id;
  private String title;
  private String description;
  private String filename;
  private Long filesize;
  private String createdAt;
  private String createdBy;
  private String createdByDevice;
  private String expiresAt;
  private String mimetype;
  private Boolean isOwn;
  private String truncatedReference;
  private String secretKey;
}
