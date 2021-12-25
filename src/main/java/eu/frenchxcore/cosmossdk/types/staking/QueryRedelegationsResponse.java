package eu.frenchxcore.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types._base.RedelegationResponse;
import eu.frenchxcore.cosmossdk.types._base.query.PageResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryRedelegationsResponse {

    @JsonProperty("delegation_responses")
    public List<RedelegationResponse> redelegationResponses;
    
    @JsonProperty("pagination")
    public PageResponse pagination;
    
}
