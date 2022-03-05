package eu.frenchxcore.messages.ibc.types.lightclients.tendermint.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.ibc.types.core.client.v1.Height;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Header {

    /**
     * 
     */
    @JsonProperty("signed_header")
    public eu.frenchxcore.messages.tendermint.types.SignedHeader signedHeader;
    
    /**
     * 
     */
    @JsonProperty("validator_set")
    public eu.frenchxcore.messages.tendermint.types.ValidatorSet validatorSet;
    
    /**
     * 
     */
    @JsonProperty("trusted_height")
    public Height trustedHeight;
    
    /**
     * 
     */
    @JsonProperty("trusted_validators")
    public eu.frenchxcore.messages.tendermint.types.ValidatorSet trustedValidators;
    
}