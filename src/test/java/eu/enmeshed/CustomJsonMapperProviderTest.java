package eu.enmeshed;

import static org.hamcrest.MatcherAssert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.enmeshed.utils.CustomJsonMapperProvider;
import lombok.Getter;
import lombok.Setter;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomJsonMapperProviderTest {

  @Setter
  @Getter
  private static class TestClass {

    private String knownProperty;
  }

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = CustomJsonMapperProvider.createObjectMapper();
  }

  @Test
  void shouldMapKnownFieldsWithNoError() {
    String json = "{ \"knownProperty\": \"value\", \"someProperty\": \"unexpected\" }";
    TestClass result = Assertions.assertDoesNotThrow(() ->
        objectMapper.readValue(json, TestClass.class)
    );
    assertThat(result, CoreMatchers.notNullValue());
    assertThat(result.getKnownProperty(), CoreMatchers.equalTo("value"));
  }
}