package eu.enmeshed;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import eu.enmeshed.endpoints.AccountEndpoint;
import eu.enmeshed.endpoints.AttributesEndpoint;
import eu.enmeshed.endpoints.FilesEndpoint;
import eu.enmeshed.endpoints.IncomingRequestsEndpoint;
import eu.enmeshed.endpoints.MessagesEndpoint;
import eu.enmeshed.endpoints.OutgoingRequestsEndpoint;
import eu.enmeshed.endpoints.RelationshipTemplatesEndpoint;
import eu.enmeshed.endpoints.RelationshipsEndpoint;
import feign.Feign;
import feign.Logger.Level;
import feign.Request.Options;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class EnmeshedClient {

  private static final ObjectMapper objectMapper =
      new ObjectMapper()
          .registerModule(new JavaTimeModule())
          .setSerializationInclusion(Include.NON_ABSENT)
          .disable(SerializationFeature.INDENT_OUTPUT)
          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
          .setDateFormat(new StdDateFormat().withColonInTimeZone(true));

  public final AccountEndpoint account;
  public final AttributesEndpoint attributes;
  public final FilesEndpoint files;
  public final MessagesEndpoint messages;
  public final RelationshipTemplatesEndpoint relationshipTemplates;
  public final RelationshipsEndpoint relationships;
  public final IncomingRequestsEndpoint incomingRequests;
  public final OutgoingRequestsEndpoint outgoingRequests;

  public EnmeshedClient(
      AccountEndpoint account,
      AttributesEndpoint attributes,
      FilesEndpoint files,
      MessagesEndpoint messages,
      RelationshipTemplatesEndpoint relationshipTemplates,
      RelationshipsEndpoint relationships,
      IncomingRequestsEndpoint incomingRequests,
      OutgoingRequestsEndpoint outgoingRequests) {
    this.account = account;
    this.attributes = attributes;
    this.files = files;
    this.messages = messages;
    this.relationshipTemplates = relationshipTemplates;
    this.relationships = relationships;
    this.incomingRequests = incomingRequests;
    this.outgoingRequests = outgoingRequests;
  }

  public static EnmeshedClient create(String url, String apiKey) {
    return create(url, apiKey, new Options(), Level.NONE);
  }

  public static EnmeshedClient create(
      String url, String apiKey, Options options, Level loggerLevel) {
    var builder =
        Feign.builder()
            .decoder(new JacksonDecoder(objectMapper))
            .encoder(new FormEncoder(new JacksonEncoder(objectMapper)))
            .requestInterceptor(request -> request.header("X-API-KEY", apiKey))
            .logLevel(loggerLevel)
            .options(options)
            .errorDecoder(new EnmeshedErrorDecoder());

    return new EnmeshedClient(
        AccountEndpoint.configure(url, builder),
        AttributesEndpoint.configure(url, builder),
        FilesEndpoint.configure(url, builder),
        MessagesEndpoint.configure(url, builder),
        RelationshipTemplatesEndpoint.configure(url, builder),
        RelationshipsEndpoint.configure(url, builder),
        IncomingRequestsEndpoint.configure(url, builder),
        OutgoingRequestsEndpoint.configure(url, builder));
  }
}
