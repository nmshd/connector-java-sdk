package eu.enmeshed.model.relationships;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({@JsonSubTypes.Type(RelationshipCreationChangeRequestContent.class)})
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public abstract class RelationshipChangeRequestContent {}
