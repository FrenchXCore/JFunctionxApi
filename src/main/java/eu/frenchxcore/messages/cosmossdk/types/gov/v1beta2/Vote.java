package eu.frenchxcore.messages.cosmossdk.types.gov.v1beta2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.List;

/**
 * Vote defines a vote on a governance proposal.
 * A Vote consists of a proposal ID, the voter, and the vote option.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vote {

    @JsonProperty("proposal_id")
    public BigInteger proposal_id;
    
    @JsonProperty("voter")
    public String voter;

    /**
     * **Deprecated.**
     * Deprecated: Prefer to use `options` instead.
     * This field is set in queries if and only if `len(options) == 1` and that option has weight 1.
     * In all other cases, this field will default to VOTE_OPTION_UNSPECIFIED.
     */
    @JsonProperty("option")
    public VoteOption option;
    
    @JsonProperty("options")
    public List<WeightedVoteOption> options;
    
}
