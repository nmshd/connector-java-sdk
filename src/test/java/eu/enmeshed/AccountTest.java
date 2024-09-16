package eu.enmeshed;

import static org.hamcrest.MatcherAssert.assertThat;

import eu.enmeshed.utils.ConnectorContainer;
import java.time.ZonedDateTime;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class AccountTest {

  @Container public ConnectorContainer connector1 = new ConnectorContainer();

  private ConnectorClient client1;

  @BeforeEach
  public void setUp() {
    client1 = ConnectorClient.create(connector1.getConnectionString(), connector1.getApiKey());
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

  @Test
  public void getSyncInfo() {
    client1.account.sync();

    var syncInfoResult = client1.account.getSyncInfo();

    var syncInfo = syncInfoResult.getResult();

    assert syncInfo.getLastSyncRun().getCompletedAt().isBefore(ZonedDateTime.now());
  }
}
