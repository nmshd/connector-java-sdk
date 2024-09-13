# Enmeshed Connector Java SDK

This SDK provides a client with corresponding data model to interact
with [Enmeshed Connector](https://enmeshed.eu/explore/connector).
On top a few use cases are also implemented to be used out of the box.

## API Client

The SDK provides a [FeignClient](https://github.com/OpenFeign/feign) implementing the REST API of Connector. The client
automatically injects the required Client Secret to all requests.

Check the [EnmeshedClient](src/main/java/eu/enmeshed/client/EnmeshedClient.java) to get a list of
currently supported methods.

### Implementation Status

| Domain                | Overall Status           | Changelog                                                                                                                                                         |
|-----------------------|--------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Monitoring            | ☹️ Not yet implemented   | n/a                                                                                                                                                               |
| Account               | 📈 Partially implemented | <ul><li>0.1.0<ul><li>add getIdentityInfo</li><li>add Trigger Sync</li></ul></li></ul>                                                                             |
| Attributes            | 📈 Partially implemented | <ul><li>0.1.0<ul><li>add searchAttributes</li><li>add createAttribute</li></ul></li></ul>                                                                         |
| Challenges            | ☹️ Not yet implemented   | n/a                                                                                                                                                               |
| Files                 | 📈 Partially implemented | <ul><li>0.4.0<ul><li>add uploadNewOwnFile</li></ul></li></ul>                                                                                                     |
| Messages              | 📈 Partially implemented | <ul><li>0.2.0<ul><li>add sendMessage</li><li>add searchMessages</li><li>add getMessageById</li></ul></li></ul>                                                    |
| Relationships         | 📈 Partially implemented | <ul><li>0.1.0<ul><li>add searchRelationships</li><li>add acceptRelationshipChange</li></ul></li><li>0.3.0<ul><li>add rejectRelationshipChange</li></ul></li></ul> |
| RelationshipTemplates | 📈 Partially implemented | <ul><li>0.1.0<ul><li>add createOwnRelationshipTemplate</li><li>add getQrCodeForRelationshipTemplate</li></ul></li></ul>                                           |
| Requests              | 📈 Partially implemented | <ul><li>0.2.0<ul><li>add createOutgoingRequest</li><li>add getOutgoingRequest</li></ul></li></ul>                                                                 |
| Tokens                | ☹️ Not yet implemented   | n/a                                                                                                                                                               |

### Setup

The client can be set up using its built in setup method:

```java
EnmeshedClient client = EnmeshedClient.configure("http://connector.local:80", "YOUR_API_KEY");
```

## Model

The SDK provides serializable Classes to wrap your data according the needs of the connector.
All classes can be instantiated in a Builder-Way which results in type safe Java-Code looking like the actual JSON
representation.

Example:

```java
ContentWrapper.containing(IdentityAttribute.builder()
    .owner("ID_OF_ATTR_OWNER")
    .value(DisplayName.builder()
        .value("MY_DISPLAY_NAME")
        .build()));
```

which will result in:

```json
{
  "content": {
    "@type": "IdentityAttribute",
    "owner": "ID_OF_ATTR_OWNER",
    "value": {
      "@type": "DisplayName",
      "value": "MY_DISPLAY_NAME"
    }
  }
}
```

### Implementation Status

| Domain                 | Overall Status              | Changelog                                                                                                                                                                                                                                                                                                                                      |
| ---------------------- | --------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | --- |
| Token                  | ☹️ Not yet implemented      |                                                                                                                                                                                                                                                                                                                                                |
| RelationshipTemplate   | 🥳 Implementation completed | <ul><li>0.1.0<ul><li>add RelationshipTemplate</li><li>add RelationshipTemplateContent</li><li>add RelationshipTemplateCreation</li></ul></li></ul>                                                                                                                                                                                             |
| Relationship           | 🥳 Implementation completed | <ul><li>0.1.0<ul><li>add Relationship</li><li>add RelationshipChange</li><li>add RelationshipChangeRequest</li><li>add RelationshipChangeResponse</li></ul></li><li>0.2.0<ul><li>refactor Response-Record to reusable Response-Class (constructor replaced by builder!)</li></ul></li></ul>                                                    |
| Messaging              | 🥳 Implementation completed | <ul><li>0.2.0<ul><li>add Message, Recipient</li><li>add MessageContent: Mail, Request, ResponseWrapper</li><li>add RelationshipChangeRequest</li><li>add RelationshipChangeResponse</li></ul></li></ul>                                                                                                                                        |
| Files                  | 📈 Partially implemented    | <ul><li>0.4.0<ul><li>add FileMetaData</li></ul></li><li>0.6.0<ul><li>fix: Add missing empty constructor</li></ul></li></ul>                                                                                                                                                                                                                    |
| Request Items          | 🥳 Implementation completed | <ul><li>0.1.0<ul><li>add RequestItemGroup</li><li>add ReadAttributeRequestItem</li><li>add ShareAttributeRequestItem</li></ul></li><li>0.2.0<ul><li>Add AuthenticationRequestItem</li><li>Add ConsentRequestItem</li><li>Add CreateAttributeRequestItem</li><li>Add FreeTextRequestItem</li><li>Add ProposeAttributeRequestItem</li></ul></ul> |
| Request                | 🥳 Implementation completed | <ul><li>0.2.0<ul><li>add Request</li><li>add RequestResponse</li><li>add RequestResponseSource</li><li>add RequestSource</li><li>add RequestWrapper</li><li>add ResponseWrapper</li></ul></li><li>0.5.0<ul><li>Rename RequestWrapper to LocalRequest</li></ul></li></ul>                                                                       |
| Response Items         | 🥳 Implementation completed | <ul><li>0.1.0<ul><li>add ResponseItemGroup</li><li>add generic Response Items (Accept, Reject, Error)</li><li>add specialized Response Items (CreateAttribute, ProposeAttribute, ReadAttribute, ShareAttribute)</li></ul></li><li>0.2.0<ul><li>Fix: Add FreeTextAcceptResponse</li></ul></li></ul>                                             |
| IdentityAttributes     | 🥳 Implementation completed | <ul><li>0.1.0<ul><li>add all IdentityAttributes</li></ul></li></ul>                                                                                                                                                                                                                                                                            |
| RelationshipAttributes | 📈 Partially implemented    | <ul><li>0.1.0<ul><li>add Base Class (not yet eligible for usage)</li></ul></li></ul>                                                                                                                                                                                                                                                           |     |
| Attribute Queries      | ☹️ Not yet implemented      |                                                                                                                                                                                                                                                                                                                                                |
| Mail                   | 🥳 Implementation completed | <ul><li>0.2.0<ul><li>add Mail</li></ul></li></ul>                                                                                                                                                                                                                                                                                              |     |
| Event                  | 🥳 Implementation completed | <ul><li>0.5.0<ul><li>add Support for Events</li><li>add LocalAttribute</li></ul></li></ul>                                                                                                                                                                                                                                                     |

### Eventing

To process Events (e.g. received per Webhook) the SDK provides the Webhook Class and an ObjectMapper instance
to deserialize received data.
The actual receiving of Events is specific for your implementation and is not covered by this SDK.

Usage:
_(Event Payload is already received and the raw JSON payload is stored in the String variable `json`)_

```java
Webhook<?> webhook = EnmeshedClient.objectMapper.readValue(json, Webhook.class);

if (webhook.getTrigger() == WebhookTrigger.CONSUMPTION__ATTRIBUTE_CREATED) {
    LocalAttribute localAttribute = (LocalAttribute) webhook.getData();
    localAttribute.getId();
}
```

To get an overview about the possible events their corresponding Payload-Datatype check the [Enmeshed Documentation](https://enmeshed.eu/integrate/connector-events).

## Out of the Box Use Cases

### Onboarding

_since 0.1.0_

The [EnmeshedOnboardingService](src/main/java/eu/enmeshed/EnmeshedOnboardingService.java) provides methods
to establish a connection between a Wallet App and the Connector.

Workflow:

- Instantiate EnmeshedOnboardingService (`new EnmeshedOnboardingService(...)`)
  - Inject [API Client Instance](#setup)
  - Provide a Display Name for the Connector (Will be displayed to the connecting peer)
  - List of Identity Attribute the connecting peer has to provide
  - List of Identity Attribute the connecting peer can provide
  - The Service will search for an already existing Attribute with the provided DisplayName or will create one
- call `generateQrCodeForRegistration(...)`
  - Provide a translated text for "requested attributes"
  - Provide a translated text for "shared attributes"
  - The Service will create a RelationshipTemplate for **Single-Use** and **1h validity**
  - The method call will return a ready to use QR-Code (Binary Data, PNG) and the RelationshipTemplateID to identify
    the new connection later
- User needs to scan the QR Code with Enmeshed App and Share the required attributes
- call `checkRegistrationState(...)`
  - The Service will trigger a sync with backbone
  - The Service checks whether there is an inbound request from the connecting peer
  - It will return `null` if no request was made by the peer
  - It will accept the inbound request if there is any
  - It will return the shared attributes, the EnmeshedAddress, the RelationshipId and the RelationshipCreationContent on
    success
- Connection between Connector and Enmeshed Wallet app is now set up.

#### Example

```java
EnmeshedClient client = EnmeshedClient.configure("http://connector.local:80", "YOUR_API_KEY");
EnmeshedOnboardingService onboardingService = new EnmeshedOnboardingService(client, "My Awesome System",
    List.of(GivenName.class, Surname.class), List.of(EMailAddress.class, StreetAddress.class));

RegistrationData registrationData = onboardingService
    .generateQrCodeForRegistration("Attributes we want to know about you", "Attribute we want to share with you");

showDataAsPng(registrationData.qrCode());

// User scans QR Code with Enmeshed App and shares the attributes

RegistrationResult registrationResult = onboardingService
    .checkRegistrationState(registrationData.relationshipTemplateId());

registrationResult.attributes().get(GivenName.class); // --> Returns the Firstname
registrationResult.attributes().get(EMailAddress.class); // --> Returns the EMail Address or null if not set
```

It's also possible to decide based on the sent attribute whether to accept or reject the relationship:

```java
// Reuse initiating relationship setup from code sample above

RegistrationResult registrationResult = onboardingService
        .checkRegistrationState(registrationData.relationshipTemplateId(), attributes -> {
          String email = attributes.get(EMailAddress.class);
          return myExternalSystem.checkEmailIsUnique(email);
        });

registrationResult.attributes().get(GivenName .class); // --> Returns the Firstname
registrationResult.attributes().get(EMailAddress .class); // --> Returns the EMail Address or null if not set
```

#### Changelog

- 0.1.0
  - add EnmeshedOnboardingService
- 0.3.0
  - add AcceptanceDecider which can be passed to `checkRegistrationState(...)` method

### Messaging

_since 0.2.0_

The [EnmeshedMessagingService](src/main/java/eu/enmeshed/EnmeshedMessagingService.java) provides methods to
communicate with an already onboarded participant of Enmeshed.

#### Send an Authentication Request

The authentication request can be used to send a message to a user. The user can then answer with an accept or reject
to this request. The [EnmeshedMessagingService](src/main/java/eu/enmeshed/EnmeshedMessagingService.java)
provides a method to conveniently send this request and receive the answer.

```java
EnmeshedClient client = EnmeshedClient.configure("http://connector.local:80", "YOUR_API_KEY");
EnmeshedMessagingService messagingService = new EnmeshedMessagingService(client);

String requestId = messagingService.sendAuthenticationRequest(
    "idXXX", // --> Enmeshed Address of the receiver
    "Your custom Message Title",
    "Your custom Message Text",
    true, // --> Flag whether the request is mandatory and the user can only accept it
    Duration.of(24, ChronoUnit.HOURS) // --> Duration how long this request is valid, aka how long the user has time to decide
    ));
```

After executing this code the user will receive the request and can answer it. To get the answer the following code can
be used:

```java
AuthenticationStatus response = messagingService.getAuthenticationStatus(requestId);
```

The result is a record containing the required information:

```java
record AuthenticationStatus(
        ZonedDateTime respondedAt, // --> Timestamp when the user has sent his answer (null if user has not answered yet)
        boolean requestExists, // --> Should be always true, otherwise the Request ID is wrong
        boolean accepted, // --> User has accepted the request
        boolean rejected, // --> User has rejected the request, If both rejected and accepted are false the user has not answered yet.
        boolean expired // --> User has not answered the request within lifetime of the request.
    ) { }
```

#### Changelog

- 0.2.0
  - add EnmeshedMessagingService
  - add SendAuthenticationRequest method
  - add getAuthenticationStatus method
- 0.2.1
  - add expired flag to AuthenticationStatus
- 0.6.0
  - add possibility to attach Metadata to Authentication-Requests
- 1.0.0
  - update SDK to Connector V5
