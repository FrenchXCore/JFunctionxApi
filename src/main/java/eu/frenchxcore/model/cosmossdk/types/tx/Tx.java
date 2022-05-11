package eu.frenchxcore.model.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Tx is the standard type used for broadcasting transactions.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tx {

    @JsonIgnore
    public String hash;
    
    /**
     * body is the processable content of the transaction
     */
    @JsonProperty("body")
    public TxBody body;

    /**
     * authInfo is the authorization related content of the transaction,
     * specifically signers, signer modes and fee
     */
    @JsonProperty("auth_info")
    public AuthInfo authInfo;

    /**
     * signatures is a list of signatures that matches the length and order of
     * AuthInfo's signer_infos to allow connecting signature meta information
     * like public key and signing mode by position.
     */
    @JsonProperty("signatures")
    public List<String> signatures;

}