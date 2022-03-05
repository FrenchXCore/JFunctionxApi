package eu.frenchxcore.messages.cosmossdk.types.gov.v1beta2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.v1beta1.Coin;

import java.math.BigInteger;
import java.util.List;

/**
 * Deposit defines an amount deposited by an account address to an active
 * proposal.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Deposit {

    @JsonProperty("proposal_id")
    public BigInteger proposal_id;
    
    @JsonProperty("depositor")
    public String depositor;
    
    @JsonProperty("amount")
    public List<Coin> amount;
    
}
