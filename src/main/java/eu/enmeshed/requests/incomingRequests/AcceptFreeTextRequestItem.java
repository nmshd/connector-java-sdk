package eu.enmeshed.requests.incomingRequests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public final class AcceptFreeTextRequestItem extends DecideRequestItemDerivation {
    @JsonProperty("accept")
    private final boolean accept = true;
    private String freeText;
}
