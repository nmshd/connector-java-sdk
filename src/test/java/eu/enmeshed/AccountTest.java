package eu.enmeshed;

import static org.hamcrest.MatcherAssert.assertThat;

import eu.enmeshed.utils.ConnectorContainer;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class AccountTest {

  @Container
  public ConnectorContainer connector1 = new ConnectorContainer();

  private EnmeshedClient client1;

  @BeforeEach
  public void setUp() {
    client1 = EnmeshedClient.create(connector1.getConnectionString(), connector1.getApiKey());
  }

  @Test
  public void getIdentityInfo() {
    var identityInfoResult = client1.account.getIdentityInfo();

    var identityInfo = identityInfoResult.getResult();

    assertThat(identityInfo.getAddress(), CoreMatchers.startsWith("did:e:"));
  }

  @Test
  public void sync() {
    client1.account.sync();
  }
}
