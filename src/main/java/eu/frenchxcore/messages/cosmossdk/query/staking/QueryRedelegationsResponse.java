package eu.frenchxcore.cosmossdk.query.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types.staking.RedelegationResponse;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryRedelegationsResponse {

    @JsonProperty("delegation_responses")
    public List<RedelegationResponse> redelegationResponses;
    
    @JsonProperty("pagination")
    public PageResponse pagination;
    
}
