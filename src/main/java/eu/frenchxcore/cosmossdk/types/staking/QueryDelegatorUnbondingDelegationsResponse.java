package eu.frenchxcore.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types._base.UnbondingDelegation;
import eu.frenchxcore.cosmossdk.types._base.query.PageResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDelegatorUnbondingDelegationsResponse {

    /**
     * 
     */
    @JsonProperty("unbonding_responses")
    public List<UnbondingDelegation> unbondingResponses;
    
    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;
    
}
