package eu.frenchxcore.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.Pool;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryPoolResponse {

    /**
     * pool defines the pool info.
     */
    @JsonProperty("pool")
    public Pool pool;

}
