package eu.frenchxcore.cosmossdk.types.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.Vote;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryVoteResponse {

    /**
     * vote defined the queried vote.
     */
    @JsonProperty("vote")
    public Vote vote;

}
