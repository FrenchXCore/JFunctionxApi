package eu.frenchxcore.cosmossdk.types.gov.v1beta2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.base.v1beta1.Coin;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Proposal defines the core field members of a governance proposal.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Proposal {

    @JsonProperty("proposal_id")
    public BigInteger proposalId;
    
    @JsonProperty("messages")
    public List<Object> messages;

    @JsonProperty("status")
    public ProposalStatus status;
    
    @JsonProperty("final_tally_result")
    public TallyResult finalTallyResult;
    
    @JsonProperty("submit_time")
    public Date submitTime;
    
    @JsonProperty("deposit_end_time")
    public Date depositEndTime;
    
    @JsonProperty("total_deposit")
    public List<Coin> totalDeposit;
    
    @JsonProperty("voting_start_time")
    public Date votingStartTime;
    
    @JsonProperty("voting_end_time")
    public Date votingEndTime;
    
}