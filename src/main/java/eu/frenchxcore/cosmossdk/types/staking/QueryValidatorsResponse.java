package eu.frenchxcore.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types._base.query.PageResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryValidatorsResponse {

    /**
     * validators contains all the queried validators.
     */
    @JsonProperty("validators")
    public List<QueryValidatorResponse> validators;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
