package eu.frenchxcore.cosmossdk.query.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.staking.Pool;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryPoolResponse {

    /**
     * pool defines the pool info.
     */
    @JsonProperty("pool")
    public Pool pool;

}
