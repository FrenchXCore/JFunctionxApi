package eu.frenchxcore.model.cosmossdk.query.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.query.PageResponse;
import eu.frenchxcore.model.cosmossdk.types.staking.Validator;

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