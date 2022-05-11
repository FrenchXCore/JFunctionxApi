package eu.frenchxcore.model.tendermint.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitSig {

    /**
     * 
     */
    @JsonProperty("block_id_flag")
    public BlockIdFlag blockIdFlag;
    
    /**
     * 
     */
    @JsonProperty("validator_address")
    public String validatorAddress;
    
    /**
     * 
     */
    @JsonProperty("timestamp")
    public Date timestamp;
    
    /**
     * 
     */
    @JsonProperty("signature")
    public String signature;
    
}