package eu.enmeshed.model.relationshipTemplates;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
  @JsonSubTypes.Type(ArbitraryRelationshipTemplateContent.class),
  @JsonSubTypes.Type(RelationshipTemplateContent.class),
})
public sealed class RelationshipTemplateContentDerivation
    permits RelationshipTemplateContent, ArbitraryRelationshipTemplateContent {}
