package eu.frenchxcore.messages.cosmossdk.query.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;
import eu.frenchxcore.messages.cosmossdk.types.staking.RedelegationResponse;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryRedelegationsResponse {

    @JsonProperty("delegation_responses")
    public List<RedelegationResponse> redelegationResponses;
    
    @JsonProperty("pagination")
    public PageResponse pagination;
    
}
