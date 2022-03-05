package eu.frenchxcore.ibc.types.lightclients.tendermint.v1;

import eu.frenchxcore.tendermint.types.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.ibc.types.core.client.v1.Height;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Header {

    /**
     * 
     */
    @JsonProperty("signed_header")
    public SignedHeader signedHeader;
    
    /**
     * 
     */
    @JsonProperty("validator_set")
    public ValidatorSet validatorSet;
    
    /**
     * 
     */
    @JsonProperty("trusted_height")
    public Height trustedHeight;
    
    /**
     * 
     */
    @JsonProperty("trusted_validators")
    public ValidatorSet trustedValidators;
    
}