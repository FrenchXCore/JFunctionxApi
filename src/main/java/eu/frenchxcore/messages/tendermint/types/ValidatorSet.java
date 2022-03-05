package eu.frenchxcore.tendermint.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.tendermint.Validator;
import java.math.BigInteger;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidatorSet {

    /**
     * 
     */
    @JsonProperty("validators")
    public List<Validator> validators;
    
    /**
     * 
     */
    @JsonProperty("proposer")
    public Validator proposer;
    
    /**
     * 
     */
    @JsonProperty("total_voting_power")
    public BigInteger totalVotingPower;
    
}
