package eu.enmeshed.utils;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.utility.DockerImageName;

public class ConnectorContainer extends GenericContainer<ConnectorContainer> {

  public static final String API_KEY = "xxx";

  public ConnectorContainer() {
    this(DockerImageName.parse("ghcr.io/nmshd/connector:latest"));
  }

  private ConnectorContainer(DockerImageName dockerImageName) {
    super(dockerImageName);

    addExposedPort(80);

    withEnv("debug", "true");

    var baseUrl = System.getenv("NMSHD_TEST_BASEURL");
    withEnv("transportLibrary:baseUrl", baseUrl);
    var clientId = System.getenv("NMSHD_TEST_CLIENTID");
    withEnv("transportLibrary:platformClientId", clientId);
    var clientSecret = System.getenv("NMSHD_TEST_CLIENTSECRET");
    withEnv("transportLibrary:platformClientSecret", clientSecret);

    var addressGenerationHostnameOverride = System.getenv("NMSHD_TEST_ADDRESS_GENERATION_HOSTNAME_OVERRIDE");
    if (addressGenerationHostnameOverride != null) {
      withEnv("transportLibrary:addressGenerationHostnameOverride", addressGenerationHostnameOverride);
    }

    withEnv("database:driver", "lokijs");
    withEnv("database:folder", "./");
    withEnv("infrastructure:httpServer:apiKey", API_KEY);

    withExtraHost("host.docker.internal", "host-gateway");

    waitingFor(new HttpWaitStrategy().forStatusCode(200).forPort(80).forPath("/health"));
  }

  public String getConnectionString() {
    return "http://" + getHost() + ":" + getMappedPort(80);
  }

  public String getApiKey() {
    return API_KEY;
  }
}
