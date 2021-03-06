package eu.frenchxcore.model.cosmossdk.query.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.staking.DelegationResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDelegationResponse {

    /**
     * delegation_responses defines the delegation info of a delegation.
     */
    @JsonProperty("delegation_response")
    public DelegationResponse delegationResponse;
    
}
