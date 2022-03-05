package eu.frenchxcore.messages.cosmossdk.query.base.tendermint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.tendermint.Validator;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;

import java.math.BigInteger;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetValidatorSetByHeightResponse {

    /**
     * 
     */
    @JsonProperty("block_height")
    public BigInteger blockHeight;
    
    /**
     * 
     */
    @JsonProperty("validators")
    public List<Validator> validators;
    
    /**
     * 
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
