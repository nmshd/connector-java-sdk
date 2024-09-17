package eu.enmeshed.requests.files;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class GetFilesQuery {

  private String createdAt;
  private String createdBy;
  private String createdByDevice;
  private String description;
  private String expiresAt;
  private String filename;
  private String filesize;
  private String mimetype;
  private String title;

  @JsonProperty("isOwn")
  private boolean own;
}
