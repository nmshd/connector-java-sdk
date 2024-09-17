package eu.enmeshed;

import static org.hamcrest.MatcherAssert.assertThat;

import eu.enmeshed.model.file.FileUploadRequest;
import eu.enmeshed.utils.ConnectorContainer;
import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class FilesTest {

  @Container
  public static ConnectorContainer connector1 = new ConnectorContainer();

  @Container
  public static ConnectorContainer connector2 = new ConnectorContainer();

  private static ConnectorClient client1;
  private static ConnectorClient client2;

  @BeforeAll
  public static void setUp() {
    client1 = ConnectorClient.create(connector1.getConnectionString(), connector1.getApiKey());
    client2 = ConnectorClient.create(connector2.getConnectionString(), connector2.getApiKey());
  }

  @Test
  public void shouldUploadAFile() {
    var expiresAt = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now().plusDays(1));

    var file =
        client1.files.uploadOwnFile(
            FileUploadRequest.builder()
                .file(new File("src/test/assets/file.txt"))
                .title("a Title")
                .expiresAt(expiresAt)
                .build());

    var fileId = file.getResult().getId();

    assertThat(fileId, CoreMatchers.startsWith("FIL"));
  }
}
