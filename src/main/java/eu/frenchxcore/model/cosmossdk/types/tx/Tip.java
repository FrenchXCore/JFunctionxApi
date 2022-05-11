package eu.frenchxcore.model.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.base.v1beta1.Coin;

import java.util.List;

/**
 * Tip is the tip used for meta-transactions.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tip {

    /**
     * amount is the amount of the tip
     */
    @JsonProperty("amount")
    public List<Coin> amount;

    /**
     * tipper is the address of the account paying for the tip
     */
    @JsonProperty("tipper")
    public String tipper;

}
