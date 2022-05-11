package eu.frenchxcore.model.cosmossdk.query.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.query.PageResponse;
import eu.frenchxcore.model.cosmossdk.types.staking.RedelegationResponse;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryRedelegationsResponse {

    @JsonProperty("delegation_responses")
    public List<RedelegationResponse> redelegationResponses;
    
    @JsonProperty("pagination")
    public PageResponse pagination;
    
}
