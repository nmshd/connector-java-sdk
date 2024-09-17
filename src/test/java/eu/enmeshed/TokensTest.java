package eu.enmeshed;

import static org.hamcrest.MatcherAssert.assertThat;

import eu.enmeshed.model.tokens.ConnectorToken;
import eu.enmeshed.requests.tokens.CreateOwnTokenRequest;
import eu.enmeshed.utils.ConnectorContainer;
import eu.enmeshed.utils.TestUtils;
import java.time.ZonedDateTime;
import java.util.HashMap;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;

public class TokensTest {

  @Container
  public static ConnectorContainer connector1 = new ConnectorContainer();

  @Container
  public static ConnectorContainer connector2 = new ConnectorContainer();

  private static ConnectorClient client1;
  private static ConnectorClient client2;

  @BeforeAll
  public static void setUp() {
    client1 = ConnectorClient.create(connector1.getConnectionString(), connector1.getApiKey());
    client2 = ConnectorClient.create(connector2.getConnectionString(), connector2.getApiKey());
  }

  @Test
  public void shouldCreateToken() {
    var token = client1.tokens.createOwnToken(CreateOwnTokenRequest.builder().content(new HashMap<>()).expiresAt(ZonedDateTime.now().plusDays(1)).build());
    var tokenId = token.getResult().getId();
    assertThat(tokenId, CoreMatchers.startsWith("TOK"));
  }

  @Test
  public void shouldGetToken() {
    var token = client1.tokens.createOwnToken(CreateOwnTokenRequest.builder().content(new HashMap<>()).expiresAt(ZonedDateTime.now().plusDays(1)).build());
    var tokenId = token.getResult().getId();

    var queriedToken = client1.tokens.getToken(tokenId);
    assertThat(queriedToken.getResult().getId(), CoreMatchers.equalTo(tokenId));
  }

  @Test
  public void shouldGetOwnTokens() {
    var token = client1.tokens.createOwnToken(CreateOwnTokenRequest.builder().content(new HashMap<>()).expiresAt(ZonedDateTime.now().plusDays(1)).build());
    var tokenId = token.getResult().getId();

    var tokens = client1.tokens.getOwnTokens(null);
    assertThat(tokens.getResult().stream().map(ConnectorToken::getId).toList(), CoreMatchers.hasItem(tokenId));
  }

  @Test
  public void shouldGetPeerTokens() {
    var token = TestUtils.exchangeToken(client1, client2);
    var tokenId = token.getId();

    var tokens = client2.tokens.getPeerTokens(null);
    assertThat(tokens.getResult().stream().map(ConnectorToken::getId).toList(), CoreMatchers.hasItem(tokenId));
  }

  @Test
  public void shouldGetQrCodeForToken() {
    var token = TestUtils.exchangeToken(client1, client2);
    var tokenId = token.getId();

    var response = client2.tokens.getQrCodeForToken(tokenId);
    assert response.body().length() > 100;
  }
}
