package eu.frenchxcore.model.cosmossdk.types.gov.v1beta1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.base.v1beta1.Coin;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Proposal {

    @JsonProperty("proposal_id")
    public BigInteger proposalId;
    
    @JsonProperty("content")
    public ProposalContent content;

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