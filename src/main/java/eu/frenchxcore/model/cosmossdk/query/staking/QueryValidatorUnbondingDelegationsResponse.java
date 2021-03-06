package eu.frenchxcore.model.cosmossdk.query.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.query.PageResponse;
import eu.frenchxcore.model.cosmossdk.types.staking.UnbondingDelegation;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryValidatorUnbondingDelegationsResponse {

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
