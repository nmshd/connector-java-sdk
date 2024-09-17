package eu.enmeshed.model.account;

import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConnectorSyncInfo {
  private SyncRun lastSyncRun;

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  @Builder
  public static class SyncRun {
    private ZonedDateTime completedAt;
  }
}
