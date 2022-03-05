package eu.frenchxcore.messages.cosmossdk.query.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;
import eu.frenchxcore.messages.cosmossdk.types.staking.Validator;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryValidatorsResponse {

    /**
     * validators contains all the queried validators.
     */
    @JsonProperty("validators")
    public List<Validator> validators;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
