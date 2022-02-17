package eu.frenchxcore.cosmossdk.query.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types.base.v1beta1.DecCoin;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryCommunityPoolResponse {

    /**
     * pool defines community pool's coins.
     */
    @JsonProperty("pool")
    public List<DecCoin> pool;
    
}
