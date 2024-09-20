package eu.enmeshed.model.relationships;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
  @JsonSubTypes.Type(ArbitraryRelationshipCreationContent.class),
  @JsonSubTypes.Type(RelationshipCreationContent.class),
})
public sealed class RelationshipCreationContentDerivation
    permits RelationshipCreationContent, ArbitraryRelationshipCreationContent {}
