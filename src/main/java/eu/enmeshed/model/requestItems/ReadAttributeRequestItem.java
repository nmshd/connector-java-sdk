package eu.enmeshed.model.requestItems;

import eu.enmeshed.model.attributes.values.AttributeValue;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ReadAttributeRequestItem extends RequestItem {

  private Map<String, String> query;

  public static <T extends AttributeValue> ReadAttributeRequestItem withIdentityAttributeQuery(
      Class<T> attributeType, boolean mustBeAccepted) {

    return ReadAttributeRequestItem.builder()
        .mustBeAccepted(mustBeAccepted)
        .query(
            Map.of("@type", "IdentityAttributeQuery", "valueType", attributeType.getSimpleName()))
        .build();
  }
}
