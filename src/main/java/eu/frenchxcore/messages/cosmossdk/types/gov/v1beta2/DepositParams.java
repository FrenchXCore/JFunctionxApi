package eu.frenchxcore.messages.cosmossdk.types.gov.v1beta2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.v1beta1.Coin;

import java.util.List;

/**
 * DepositParams defines the params for deposits on governance proposals.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositParams {

    /**
     * Minimum deposit for a proposal to enter voting period.
     */
    @JsonProperty("min_deposit")
    public List<Coin> minDeposit;

    /**
     * Maximum period for FX holders to deposit on a proposal. Initial value: "1209600s"
     */
    @JsonProperty("max_deposit_period")
    public String maxDepositPeriod;

}
