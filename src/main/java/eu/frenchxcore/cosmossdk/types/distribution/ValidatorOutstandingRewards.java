package eu.frenchxcore.cosmossdk.types.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.Coin;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidatorOutstandingRewards {

    @JsonProperty("rewards")
    public List<Coin> rewards;
    
}
