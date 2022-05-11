package eu.frenchxcore.model.cosmossdk.query.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.base.v1beta1.DecCoin;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryCommunityPoolResponse {

    /**
     * pool defines community pool's coins.
     */
    @JsonProperty("pool")
    public List<DecCoin> pool;
    
}
