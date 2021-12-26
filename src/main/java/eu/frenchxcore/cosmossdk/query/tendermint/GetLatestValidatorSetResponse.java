package eu.frenchxcore.cosmossdk.query.tendermint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;
import eu.frenchxcore.cosmossdk.types.tendermint.Validator;
import java.math.BigInteger;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLatestValidatorSetResponse {

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
