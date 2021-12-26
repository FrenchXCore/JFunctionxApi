package eu.frenchxcore.cosmossdk.query.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDelegatorWithdrawAddressResponse {

    /**
     * withdraw_address defines the delegator address to query for.
     */
    @JsonProperty("withdraw_address")
    public String withdrawAddress;
    
}
