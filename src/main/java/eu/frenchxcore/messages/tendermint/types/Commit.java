package eu.frenchxcore.tendermint.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {

    /**
     * 
     */
    @JsonProperty("height")
    public BigInteger height;
    
    /**
     * 
     */
    @JsonProperty("round")
    public Long round;
    
    /**
     * 
     */
    @JsonProperty("block_id")
    public BlockID blockID;
    
    /**
     * 
     */
    @JsonProperty("signatures")
    public List<CommitSig> signatures;

}