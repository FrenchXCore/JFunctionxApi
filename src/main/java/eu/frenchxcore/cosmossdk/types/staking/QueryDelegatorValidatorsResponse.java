package eu.frenchxcore.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.Validator;
import eu.frenchxcore.cosmossdk.types._base.query.PageResponse;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDelegatorValidatorsResponse {

    /**
     * validators defines the the validators' info of a delegator.
     */
    @JsonProperty("validators")
    public List<Validator> validators;
    
    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;
    
}