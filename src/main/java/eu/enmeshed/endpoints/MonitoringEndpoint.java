package eu.enmeshed.endpoints;

import eu.enmeshed.model.monitoring.ConnectorHealth;
import eu.enmeshed.model.monitoring.ConnectorRequestCount;
import eu.enmeshed.model.monitoring.ConnectorSupportInformation;
import eu.enmeshed.model.monitoring.ConnectorVersionInfo;
import feign.Feign.Builder;
import feign.Headers;
import feign.RequestLine;

public interface MonitoringEndpoint {

  static MonitoringEndpoint configure(String url, Builder builder) {
    return builder.target(MonitoringEndpoint.class, url);
  }

  @RequestLine("GET /health")
  @Headers("Accept: application/json")
  ConnectorHealth getHealth();

  @RequestLine("GET /Monitoring/Version")
  @Headers("Accept: application/json")
  ConnectorVersionInfo getVersionInfo();

  @RequestLine("GET /Monitoring/Requests")
  @Headers("Accept: application/json")
  ConnectorRequestCount getRequests();

  @RequestLine("POST /Monitoring/Support")
  @Headers("Accept: application/json")
  ConnectorSupportInformation getSupportInformation();
}
