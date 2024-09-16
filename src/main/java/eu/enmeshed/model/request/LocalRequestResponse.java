package eu.enmeshed.model.request;

import eu.enmeshed.model.ContentWrapper;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class LocalRequestResponse extends ContentWrapper<Response> {

  private ZonedDateTime createdAt;
  private RequestResponseSource source;
}
