package eu.frenchxcore.model.cosmossdk.query.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.gov.v1beta1.Vote;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryVoteResponse {

    /**
     * vote defined the queried vote.
     */
    @JsonProperty("vote")
    public Vote vote;

}
