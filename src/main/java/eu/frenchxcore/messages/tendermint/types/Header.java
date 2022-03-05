package eu.frenchxcore.tendermint.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Header {

    /**
     * 
     */
    @JsonProperty("version")
    public ConsensusVersion version;
    
    /**
     * 
     */
    @JsonProperty("chain_id")
    public String chainID;
    
    /**
     * 
     */
    @JsonProperty("height")
    public BigInteger height;
    
    /**
     * 
     */
    @JsonProperty("time")
    public Date time;
    
    /**
     * 
     */
    @JsonProperty("last_block_id")
    public BlockID lastBlockID;
    
    /**
     * 
     */
    @JsonProperty("last_commit_hash")
    public String lastCommitHash;
    
    /**
     * 
     */
    @JsonProperty("data_hash")
    public String dataHash;
    
    /**
     * 
     */
    @JsonProperty("validators_hash")
    public String validatorsHash;
    
    /**
     * 
     */
    @JsonProperty("next_validators_hash")
    public String nextValidatorsHash;
    
    /**
     * 
     */
    @JsonProperty("consensus_hash")
    public String consensusHash;
    
    /**
     * 
     */
    @JsonProperty("app_hash")
    public String appHash;
    
    /**
     * 
     */
    @JsonProperty("last_results_hash")
    public String lastResultsHash;
    
    /**
     * 
     */
    @JsonProperty("evidence_hash")
    public String evidenceHash;
    
    /**
     * 
     */
    @JsonProperty("proposer_address")
    public String proposerAddress;
    
}