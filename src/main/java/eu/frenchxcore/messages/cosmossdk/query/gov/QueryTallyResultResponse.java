package eu.frenchxcore.messages.cosmossdk.query.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.gov.v1beta1.TallyResult;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryTallyResultResponse {

    /**
     * tally defines the requested tally.
     */
    @JsonProperty("tally")
    public TallyResult tally;

}
