package eu.enmeshed.model.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.enmeshed.model.ObjectReference;
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

  @JsonProperty("isOwn")
  private boolean own;

  private String truncatedReference;
  private ObjectReference reference;
  private String secretKey;
}
