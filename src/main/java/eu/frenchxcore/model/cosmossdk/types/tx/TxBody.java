package eu.frenchxcore.model.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * TxBody is the body of a transaction that all signers sign over.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxBody {

    /**
     * messages is a list of messages to be executed. The required signers of those messages define the number and order of elements in AuthInfo's signer_infos and Tx's signatures. Each required signer address is added to the list only the first time it occurs. By convention, the first required signer (usually from the first message) is referred to as the primary signer and pays the fee for the whole transaction.
     */
    @JsonProperty("messages")
    //public List<Map<String, Object>> messages;
    public List<eu.frenchxcore.model.BaseMessage> messages;

    /**
     * memo is any arbitrary note/comment to be added to the transaction. WARNING: in clients, any publicly exposed text should not be called memo, but should be called note instead (see https://github.com/cosmos/cosmos-sdk/issues/9122).
     */
    @JsonProperty("memo")
    public String memo;

    /**
     * timeoutHeight is the block height after which this transaction will not be processed by the chain
     */
    @JsonProperty("timeout_height")
    public BigInteger timeoutHeight;

    /**
     * extensionOptions are arbitrary options that can be added by chains when the default options are not sufficient. If any of these are present and can't be handled, the transaction will be rejected
     */
    @JsonProperty("extension_options")
    public List<Map<String,Object>> extensionOptions;

    /**
     * nonCriticalExtensionOptions are arbitrary options that can be added by chains when the default options are not sufficient. If any of these are present and can't be handled, they will be ignored
     */
    @JsonProperty("non_critical_extension_options")
    public List<Map<String,Object>> nonCriticalExtensionOptions;

}
