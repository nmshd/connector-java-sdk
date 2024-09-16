package eu.enmeshed.requests.files;

import lombok.Builder;

@Builder
public class GetPeerFilesQuery {

  private String createdAt;
  private String createdBy;
  private String description;
  private String expiresAt;
  private String filename;
  private String filesize;
  private String mimetype;
  private String title;
}
