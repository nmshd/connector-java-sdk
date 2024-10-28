package eu.enmeshed.requests.files;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoadPeerFileRequest {

  private String reference;
}
