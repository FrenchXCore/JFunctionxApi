package eu.frenchxcore.tendermint.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.base.tendermint.Validator;
import java.math.BigInteger;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignedHeader {

    /**
     * 
     */
    @JsonProperty("header")
    public Header header;
    
    /**
     * 
     */
    @JsonProperty("commit")
    public Commit commit;
    
}
