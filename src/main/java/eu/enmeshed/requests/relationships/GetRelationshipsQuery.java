package eu.enmeshed.requests.relationships;

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
public class GetRelationshipsQuery {

  private String templateId;
  private String peer;
  private String status;
}
