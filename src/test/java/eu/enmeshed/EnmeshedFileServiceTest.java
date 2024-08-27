package eu.enmeshed;

import static eu.enmeshed.model.ResultWrapper.containing;
import static eu.enmeshed.model.file.FileMetaData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import eu.enmeshed.client.EnmeshedClient;
import eu.enmeshed.model.file.FileMetaData;
import eu.enmeshed.model.file.FileReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EnmeshedFileServiceTest {
  private static final String TEST_FILE_ID = "testId";
  private static final FileMetaData EXPECTED_TEST_FILE_METADATA =
      builder().id(TEST_FILE_ID).title("Test data").build();
  @Mock EnmeshedClient enmeshedClientMock;
  EnmeshedFileService enmeshedFileService;

  @BeforeEach
  void setup() {
    enmeshedFileService = new EnmeshedFileService(enmeshedClientMock);
  }

  @Test
  void shouldCorrectlyGetFileMetadataByFileId() {
    when(enmeshedClientMock.getFileMetadataByFileId(eq(TEST_FILE_ID)))
        .thenReturn(containing(EXPECTED_TEST_FILE_METADATA));

    FileMetaData actualFileMetadata = enmeshedFileService.getFileMetadataByFileId(TEST_FILE_ID);

    assertSame(EXPECTED_TEST_FILE_METADATA, actualFileMetadata);
  }

  @Test
  void shouldCorrectlyGetFileMetadataByReference() {
    when(enmeshedClientMock.getFileMetadataByReference(any(FileReference.class)))
        .thenReturn(containing(EXPECTED_TEST_FILE_METADATA));

    FileMetaData actualFileMetadata = enmeshedFileService.getFileMetadataByReference(TEST_FILE_ID);

    assertSame(EXPECTED_TEST_FILE_METADATA, actualFileMetadata);
  }
}
