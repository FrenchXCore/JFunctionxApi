package eu.frenchxcore.messages.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.PublicKey;
import java.math.BigInteger;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Validator {

    /**
     * operator_address defines the address of the validator's operator; bech encoded in JSON.
     */
    @JsonProperty("operator_address")
    public String operatorAddress;
    
    /**
     * consensus_pubkey is the consensus public key of the validator, as a Protobuf Any.
     */
    @JsonProperty("consensus_pubkey")
    public PublicKey consensusPubkey;
    
    /**
     * jailed defined whether the validator has been jailed from bonded status or not.
     */
    @JsonProperty("jailed")
    public Boolean jailed;
    
    /**
     * status is the validator status (bonded/unbonding/unbonded).
     */
    @JsonProperty("status")
    public BondStatus status;
    
    /**
     * tokens define the delegated tokens (incl. self-delegation).
     */
    @JsonProperty("tokens")
    public String tokens;
    
    /**
     * delegator_shares defines total shares issued to a validator's delegators.
     */
    @JsonProperty("delegator_shares")
    public String delegatorShares;
    
    /**
     * description defines the description terms for the validator.
     */
    @JsonProperty("description")
    public Description description;
    
    /**
     * unbonding_height defines, if unbonding, the height at which this validator has begun unbonding.
     */
    @JsonProperty("unbonding_height")
    public BigInteger unbondingHeight;
    
    /**
     * unbonding_time defines, if unbonding, the min time for the validator to complete unbonding.
     */
    @JsonProperty("unbonding_time")
    public Date unbondingTime;
    
    /**
     * commission defines the commission parameters.
     */
    @JsonProperty("commission")
    public Commission commission;
    
    /**
     * min_self_delegation is the validator's self declared minimum self delegation.
     */
    @JsonProperty("min_self_delegation")
    public String minSelfDelegation;
    
}