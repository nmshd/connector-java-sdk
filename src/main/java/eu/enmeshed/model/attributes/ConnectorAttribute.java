package eu.enmeshed.model.attributes;

import eu.enmeshed.model.ContentWrapper;
import eu.enmeshed.model.event.WebhookData;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ConnectorAttribute extends ContentWrapper<Attribute> implements WebhookData {

  private String id;

  private String parentId;

  private ZonedDateTime createdAt;

  private String succeeds;

  private String succeededBy;

  private ConnectorAttributeShareInfo shareInfo;
}
