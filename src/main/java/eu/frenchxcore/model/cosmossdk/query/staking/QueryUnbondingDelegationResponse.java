package eu.frenchxcore.model.cosmossdk.query.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.staking.UnbondingDelegation;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryUnbondingDelegationResponse {

    /**
     * unbond defines the unbonding information of a delegation.
     */
    @JsonProperty("unbond")
    public UnbondingDelegation unbond;
    
}
