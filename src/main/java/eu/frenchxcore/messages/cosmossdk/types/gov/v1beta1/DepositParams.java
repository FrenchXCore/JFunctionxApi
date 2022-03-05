package eu.frenchxcore.messages.cosmossdk.types.gov.v1beta1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.v1beta1.Coin;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositParams {

    @JsonProperty("min_deposit")
    public List<Coin> minDeposit;
    
    @JsonProperty("max_deposit_period")
    public String maxDepositPeriod;     // "max_deposit_period": "1209600s"

}
