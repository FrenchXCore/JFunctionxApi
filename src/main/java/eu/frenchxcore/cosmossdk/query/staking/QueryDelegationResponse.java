package eu.frenchxcore.cosmossdk.query.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.staking.DelegationResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDelegationResponse {

    /**
     * delegation_responses defines the delegation info of a delegation.
     */
    @JsonProperty("delegation_response")
    public DelegationResponse delegationResponse;
    
}
