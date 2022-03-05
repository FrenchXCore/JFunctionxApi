package eu.frenchxcore.messages.cosmossdk.query.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;
import eu.frenchxcore.messages.cosmossdk.types.staking.DelegationResponse;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDelegatorDelegationsResponse {

    /**
     * delegation_responses defines all the delegations' info of a delegator.
     */
    @JsonProperty("delegation_responses")
    public List<DelegationResponse> delegationResponses;
    
    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;
    
}
