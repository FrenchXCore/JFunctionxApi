package eu.frenchxcore.cosmossdk.types.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.Coin;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Proposal {

    @JsonProperty("proposal_id")
    public BigInteger proposalId;
    
    @JsonProperty("content")
    public Object content;
    /**
        "content": {
          "@type": "/fx.gravity.crosschain.v1.InitCrossChainParamsProposal",
          "title": "TRON support",
          "description": "Summary:\nFunction X foundation is proposing to deploy TRON onto Function X. This will expand TRON's ecosystem and liquidity into Function X ecosystem (incl. PundiXChain, FXCore, etc)\n\nTimeline:\nVoting will last 14 days, if approved the foundation will take 7 days to deploy, hence a total of 21 days from the day this proposal is submitted for TRON to go live, if voting is in favor FOR this proposal.\n\nVote YES for: TRON will be bridged into Function X.\nVote NO for: TRON will not be bridged into Function X.",
          "params": {
            "gravity_id": "fx-tron-bridge",
            "average_block_time": "5000",
            "external_batch_timeout": "43200000",
            "average_external_block_time": "3000",
            "signed_window": "20000",
            "slash_fraction": "0.001000000000000000",
            "oracle_set_update_power_change_percent": "0.100000000000000000",
            "ibc_transfer_timeout_height": "20000",
            "oracles": [
              "fx1gmhfhmscngtxvdcsm0753txnz5vp8kzjmhchcj",
              "fx19f7lft94qkkkj0ht4kg5nz3rmrwtzarqfdvaj3",
              "fx19s08ghjh5g55x2ffqj6c0l378zshcvskxjacra",
              "fx15m5esmf347eq7mvg9438qx5m8l8f0564y7d8zy",
              "fx1w65el5ta9v7y6h46qmx4cg0jpu5aq24w6lnvjf",
              "fx1t6qzk2293ukpunruu9npagczc2hepmurh7drtv",
              "fx15e8f7nwqlk02jhjlccgvnc093ajumetsl9uj9p",
              "fx1h86slytsgl6g60jvsg3kjfsrmxysmhskjn5pzg",
              "fx13m0dtdz3yusf365jcy7y57n8ql80s52w39wjfy",
              "fx1zxsk6wwxskdlxq69hgs52t4enf2mvd59h7ze7v"
            ],
            "deposit_threshold": {
              "denom": "FX",
              "amount": "10000000000000000000000"
            }
          },
          "chain_name": "tron"
        }
    */
    
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
    public Date withdrawAddrEnabled;
    
}