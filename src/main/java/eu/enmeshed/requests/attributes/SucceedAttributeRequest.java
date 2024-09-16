package eu.enmeshed.requests.attributes;

import eu.enmeshed.model.attributes.values.AttributeValue;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SucceedAttributeRequest {

  private SuccessorContent successorContent;

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Builder
  static class SuccessorContent {

    private AttributeValue value;
    private List<String> tags;
    private ZonedDateTime validFrom;
    private ZonedDateTime validTo;
  }
}
