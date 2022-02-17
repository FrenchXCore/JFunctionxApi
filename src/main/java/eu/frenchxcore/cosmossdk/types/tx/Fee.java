package eu.frenchxcore.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.base.v1beta1.Coin;
import java.math.BigInteger;
import java.util.List;

/**
 * Fee includes the amount of coins paid in fees and the maximum gas to be used
 * by the transaction. The ratio yields an effective "gasprice", which must be
 * above some miminum to be accepted into the mempool.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fee {

    /**
     * amount is the amount of coins to be paid as a fee
     */
    @JsonProperty("amount")
    public List<Coin> amount;

    /**
     * gasLimit is the maximum gas that can be used in transaction processing
     * before an out of gas error occurs
     */
    @JsonProperty("gas_limit")
    public BigInteger gasLimit;

    /**
     * if unset, the first signer is responsible for paying the fees. If set,
     * the specified account must pay the fees. the payer must be a tx signer
     * (and thus have signed this field in AuthInfo). setting this field does
     * not change the ordering of required signers for the transaction.
     */
    @JsonProperty("payer")
    public String payer;

    /**
     * if set, the fee payer (either the first signer or the value of the payer
     * field) requests that a fee grant be used to pay fees instead of the fee
     * payer's own balance. If an appropriate fee grant does not exist or the
     * chain does not support fee grants, this will fail
     */
    @JsonProperty("granter")
    public String granter;

}
