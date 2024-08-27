package eu.enmeshed.model.file;

import feign.form.FormProperty;
import java.io.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class FileUploadRequest {

  @FormProperty("file")
  private File file;

  @FormProperty("title")
  private String title;

  @FormProperty("description")
  private String description;

  @FormProperty("expiresAt")
  private String expiresAt;
}
