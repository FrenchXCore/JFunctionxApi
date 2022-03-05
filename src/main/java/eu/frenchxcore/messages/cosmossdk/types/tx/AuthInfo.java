package eu.frenchxcore.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * AuthInfo describes the fee and signer modes that are used to sign a
 * transaction.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthInfo {

    /**
     * signer_infos defines the signing modes for the required signers. The
     * number and order of elements must match the required signers from
     * TxBody's messages. The first element is the primary signer and the one
     * which pays the fee.
     */
    @JsonProperty("signer_infos")
    public List<SignerInfo> signerInfos;

    /**
     * Fee is the fee and gas limit for the transaction. The first signer is the
     * primary signer and the one which pays the fee. The fee can be calculated
     * based on the cost of evaluating the body and doing signature verification
     * of the signers. This can be estimated via simulation.
     */
    @JsonProperty("fee")
    public Fee fee;

    /**
     * Tip is the optional tip used for meta-transactions.
     */
    @JsonProperty("tip")
    public Tip tip;

}
