package eu.enmeshed;

import eu.enmeshed.client.EnmeshedClient;
import eu.enmeshed.model.file.FileMetaData;
import eu.enmeshed.model.file.FileReference;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class EnmeshedFileService {
  private final EnmeshedClient enmeshedClient;

  @SneakyThrows
  public byte[] downloadFileById(String fileId) {
    Response fileResponse = enmeshedClient.getFileResponseById(fileId);
    return fileResponse.body().asInputStream().readAllBytes();
  }

  public FileMetaData getFileMetadataByFileId(String fileId) {
    return enmeshedClient.getFileMetadataByFileId(fileId).getResult();
  }

  public FileMetaData getFileMetadataByReference(String reference) {
    return enmeshedClient
        .getFileMetadataByReference(FileReference.builder().reference(reference).build())
        .getResult();
  }
}
