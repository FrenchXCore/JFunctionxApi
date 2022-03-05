package eu.frenchxcore.messages.cosmossdk.query.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDelegatorValidatorsResponse {

    /**
     * validators defines the validators a delegator is delegating for.
     */
    @JsonProperty("validators")
    public List<String> validators;
    
}
