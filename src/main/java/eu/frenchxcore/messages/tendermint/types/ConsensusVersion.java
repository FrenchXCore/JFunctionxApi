package eu.frenchxcore.messages.tendermint.types;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

public class ConsensusVersion {
    
    /**
     * 
     */
    @JsonProperty("block")
    public BigInteger block;
    
    /**
     * 
     */
    @JsonProperty("app")
    public BigInteger app;
    
}
