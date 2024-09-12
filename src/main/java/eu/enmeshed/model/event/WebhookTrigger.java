package eu.enmeshed.model.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WebhookTrigger {
  @JsonProperty(WebhookTriggerNames.Consumption.ATTRIBUTE_CREATED)
  CONSUMPTION__ATTRIBUTE_CREATED,

  @JsonProperty(WebhookTriggerNames.Consumption.ATTRIBUTE_DELETED)
  CONSUMPTION__ATTRIBUTE_DELETED,

  @JsonProperty(WebhookTriggerNames.Consumption.ATTRIBUTE_SUCCEEDED)
  CONSUMPTION__ATTRIBUTE_SUCCEEDED,

  @JsonProperty(WebhookTriggerNames.Consumption.ATTRIBUTE_UPDATED)
  CONSUMPTION__ATTRIBUTE_UPDATED,

  @JsonProperty(WebhookTriggerNames.Consumption.INCOMING_REQUEST_RECEIVED)
  CONSUMPTION__INCOMING_REQUEST_RECEIVED,

  @JsonProperty(WebhookTriggerNames.Consumption.INCOMING_REQUEST_STATUS_CHANGED)
  CONSUMPTION__INCOMING_REQUEST_STATUS_CHANGED,

  @JsonProperty(WebhookTriggerNames.Consumption.MESSAGE_PROCESSED)
  CONSUMPTION__MESSAGE_PROCESSED,

  @JsonProperty(WebhookTriggerNames.Consumption.OUTGOING_REQUEST_CREATED)
  CONSUMPTION__OUTGOING_REQUEST_CREATED,

  @JsonProperty(WebhookTriggerNames.Consumption.OUTGOING_REQUEST_CREATED_AND_COMPLETED)
  CONSUMPTION__OUTGOING_REQUEST_CREATED_AND_COMPLETED,

  @JsonProperty(
      WebhookTriggerNames.Consumption
          .OUTGOING_REQUEST_FROM_RELATIONSHIP_CREATION_CREATED_AND_COMPLETED)
  CONSUMPTION__OUTGOING_REQUEST_FROM_RELATIONSHIP_CREATION_CREATED_AND_COMPLETED,

  @JsonProperty(WebhookTriggerNames.Consumption.OUTGOING_REQUEST_STATUS_CHANGED)
  CONSUMPTION__OUTGOING_REQUEST_STATUS_CHANGED,

  @JsonProperty(WebhookTriggerNames.Consumption.RELATIONSHIP_TEMPLATE_PROCESSED)
  CONSUMPTION__RELATIONSHIP_TEMPLATE_PROCESSED,

  @JsonProperty(WebhookTriggerNames.Consumption.SHARED_ATTRIBUTE_COPY_CREATED)
  CONSUMPTION__SHARED_ATTRIBUTE_COPY_CREATED,

  @JsonProperty(WebhookTriggerNames.Transport.MESSAGE_RECEIVED)
  TRANSPORT__MESSAGE_RECEIVED,

  @JsonProperty(WebhookTriggerNames.Transport.MESSAGE_SENT)
  TRANSPORT__MESSAGE_SENT,

  @JsonProperty(WebhookTriggerNames.Transport.PEER_RELATIONSHIP_TEMPLATE_LOADED)
  TRANSPORT__PEER_RELATIONSHIP_TEMPLATE_LOADED,

  @JsonProperty(WebhookTriggerNames.Transport.RELATIONSHIP_CHANGED)
  TRANSPORT__RELATIONSHIP_CHANGED;

  @Override
  public String toString() {
    try {
      return WebhookTrigger.class.getField(this.name()).getAnnotation(JsonProperty.class).value();
    } catch (NoSuchFieldException e) {
      return null;
    }
  }
}
