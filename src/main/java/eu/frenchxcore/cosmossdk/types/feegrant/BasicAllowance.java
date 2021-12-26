package eu.frenchxcore.cosmossdk.types.feegrant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.Coin;
import java.util.Date;
import java.util.List;

/**
 * BasicAllowance implements Allowance with a one-time grant of tokens that
 * optionally expires. The grantee can use up to SpendLimit to cover fees.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicAllowance {

    /**
     * spend_limit specifies the maximum amount of tokens that can be spent by
     * this allowance and will be updated as tokens are spent. If it is empty,
     * there is no spend limit and any amount of coins can be spent.
     */
    @JsonProperty("spend_limit")
    public List<Coin> spendLimit;

    /**
     * expiration specifies an optional time when this allowance expires.
     */
    @JsonProperty("expiration")
    public Date expiration;

}
