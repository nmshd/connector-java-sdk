package eu.enmeshed.requests.attributes;

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
public class CreateRepositoryAttributeRequest {

  private CreateRepositoryAttributeRequestContent content;

  public static CreateRepositoryAttributeRequest containing(CreateRepositoryAttributeRequestContent content) {

    return new CreateRepositoryAttributeRequest(content);
  }
}

