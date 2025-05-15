package eu.enmeshed.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomJsonMapperProvider {

  public static final String UNKNOWN_PROPERTY_LOG_FORMAT = "Unknown property '{}' encountered in JSON response for class '{}'.";

  public static ObjectMapper createObjectMapper() {
    return JsonMapper.builder()
        .addModule(new JavaTimeModule())
        .serializationInclusion(Include.NON_ABSENT)
        .disable(SerializationFeature.INDENT_OUTPUT)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .defaultDateFormat(new StdDateFormat().withColonInTimeZone(true))
        .addHandler(new CustomDeserializationProblemHandler())
        .build();
  }

  private static class CustomDeserializationProblemHandler extends DeserializationProblemHandler {

    @Override
    public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser p, JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException {
      log.warn(UNKNOWN_PROPERTY_LOG_FORMAT,
          propertyName, beanOrClass.getClass().getSimpleName());
      p.skipChildren();
      return true;
    }
  }
}
