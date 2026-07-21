package eu.enmeshed.endpoints;

import eu.enmeshed.ConnectorResponse;
import eu.enmeshed.model.file.ConnectorFile;
import eu.enmeshed.model.file.FileUploadRequest;
import eu.enmeshed.model.tokens.ConnectorToken;
import eu.enmeshed.requests.files.GetFilesQuery;
import eu.enmeshed.requests.files.GetOwnFilesQuery;
import eu.enmeshed.requests.files.GetPeerFilesQuery;
import eu.enmeshed.requests.files.LoadPeerFileRequest;
import feign.Feign.Builder;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import feign.Response;

public interface FilesEndpoint {

  static FilesEndpoint configure(String url, Builder builder) {
    return builder.target(FilesEndpoint.class, url);
  }

  @RequestLine("GET /api/core/v1/Files")
  @Headers("Accept: application/json")
  ConnectorResponse<ConnectorFile[]> getFiles(@QueryMap GetFilesQuery query);

  @RequestLine("POST /api/core/v1/Files/Own")
  @Headers({"Content-Type:  multipart/form-data"})
  ConnectorResponse<ConnectorFile> uploadOwnFile(FileUploadRequest fileUploadRequest);

  @RequestLine("GET /api/core/v1/Files/Own")
  @Headers("Accept: application/json")
  ConnectorResponse<ConnectorFile[]> getOwnFiles(@QueryMap GetOwnFilesQuery query);

  @RequestLine("POST /api/core/v1/Files/Peer")
  @Headers("Content-Type: application/json")
  ConnectorResponse<ConnectorFile> loadPeerFile(LoadPeerFileRequest fileUploadRequest);

  @RequestLine("GET /api/core/v1/Files/Peer")
  @Headers("Accept: application/json")
  ConnectorResponse<ConnectorFile[]> getPeerFiles(@QueryMap GetPeerFilesQuery query);

  @RequestLine("GET /api/core/v1/Files/{fileId}")
  @Headers("Accept: application/json")
  ConnectorResponse<ConnectorFile> getFile(@Param("fileId") String fileId);

  @RequestLine("GET /api/core/v1/Files/{fileId}/Download")
  @Headers("Accept: application/json")
  Response downloadFile(@Param("fileId") String fileId);

  @RequestLine("DELETE /api/core/v1/Files/{fileId}")
  @Headers("Accept: image/png")
  Response getQrCodeForFile(@Param("fileId") String fileId);

  @RequestLine("DELETE /api/core/v1/Files/{fileId}/Token")
  @Headers("Accept: application/json")
  ConnectorResponse<ConnectorToken> createTokenForFile(@Param("fileId") String fileId);

  @RequestLine("DELETE /api/core/v1/Files/{fileId}/Token")
  @Headers("Accept: image/png")
  Response createTokenQrCodeForFile(@Param("fileId") String fileId);
}
