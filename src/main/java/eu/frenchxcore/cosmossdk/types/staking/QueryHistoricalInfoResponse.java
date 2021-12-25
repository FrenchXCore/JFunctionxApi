package eu.frenchxcore.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.HistoricalInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryHistoricalInfoResponse {

    /**
     * hist defines the historical info at the given height.
     */
    @JsonProperty("hist")
    public HistoricalInfo hist;

}
