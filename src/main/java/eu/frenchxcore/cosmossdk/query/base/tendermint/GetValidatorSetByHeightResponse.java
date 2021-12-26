package eu.frenchxcore.cosmossdk.query.base.tendermint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;
import eu.frenchxcore.cosmossdk.types.base.tendermint.Validator;
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
