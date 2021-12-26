package eu.frenchxcore.cosmossdk.query.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.gov.TallyResult;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryTallyResultResponse {

    /**
     * tally defines the requested tally.
     */
    @JsonProperty("tally")
    public TallyResult tally;

}