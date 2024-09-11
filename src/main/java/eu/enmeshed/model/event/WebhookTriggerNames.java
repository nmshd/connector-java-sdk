package eu.enmeshed.model.event;

public abstract class WebhookTriggerNames {

  public abstract static class Consumption {
    public static final String ATTRIBUTE_CREATED = "consumption.attributeCreated";
    public static final String ATTRIBUTE_DELETED = "consumption.attributeDeleted";
    public static final String ATTRIBUTE_SUCCEEDED = "consumption.attributeSucceeded";
    public static final String ATTRIBUTE_UPDATED = "consumption.attributeUpdated";
    public static final String INCOMING_REQUEST_RECEIVED = "consumption.incomingRequestReceived";
    public static final String INCOMING_REQUEST_STATUS_CHANGED =
        "consumption.incomingRequestStatusChanged";
    public static final String MESSAGE_PROCESSED = "consumption.messageProcessed";
    public static final String OUTGOING_REQUEST_CREATED = "consumption.outgoingRequestCreated";
    public static final String OUTGOING_REQUEST_CREATED_AND_COMPLETED =
        "consumption.outgoingRequestCreatedAndCompleted";
    public static final String OUTGOING_REQUEST_FROM_RELATIONSHIP_CREATION_CREATED_AND_COMPLETED =
        "consumption.outgoingRequestFromRelationshipCreationCreatedAndCompleted";
    public static final String OUTGOING_REQUEST_STATUS_CHANGED =
        "consumption.outgoingRequestStatusChanged";
    public static final String RELATIONSHIP_TEMPLATE_PROCESSED =
        "consumption.relationshipTemplateProcessed";
    public static final String SHARED_ATTRIBUTE_COPY_CREATED =
        "consumption.sharedAttributeCopyCreated";
  }

  public abstract static class Transport {
    public static final String MESSAGE_RECEIVED = "transport.messageReceived";
    public static final String MESSAGE_SENT = "transport.messageSent";
    public static final String PEER_RELATIONSHIP_TEMPLATE_LOADED =
        "transport.peerRelationshipTemplateLoaded";
    public static final String RELATIONSHIP_CHANGED = "transport.relationshipChanged";
  }
}
