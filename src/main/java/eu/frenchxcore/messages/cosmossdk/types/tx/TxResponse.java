package eu.frenchxcore.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.util.List;

/**
 * TxResponse defines a structure containing relevant tx data and metadata. The
 * tags are stringified and the log is JSON decoded.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxResponse {

    /**
     * The block height
     */
    @JsonProperty("height")
    public BigInteger height;

    /**
     * The transaction hash.
     */
    @JsonProperty("txhash")
    public String txhash;

    /**
     * Namespace for the Code
     */
    @JsonProperty("codespace")
    public String codespace;

    /**
     * Response code.
     */
    @JsonProperty("code")
    public Long code;

    /**
     * Result bytes, if any.
     */
    @JsonProperty("data")
    public String data;

    /**
     * The output of the application's logger (raw string). May be
     * non-deterministic.
     */
    @JsonProperty("raw_log")
    public String rawLog;

    /**
     * The output of the application's logger (typed). May be non-deterministic.
     */
    @JsonProperty("logs")
    public List<ABCIMessageLog> logs;

    /**
     * Additional information. May be non-deterministic.
     */
    @JsonProperty("info")
    public String info;

    /**
     * Amount of gas requested for transaction.
     */
    @JsonProperty("gas_wanted")
    public BigInteger gasWanted;

    /**
     * Amount of gas consumed by transaction.
     */
    @JsonProperty("gas_used")
    public BigInteger gasUsed;

    /**
     * The request transaction bytes.
     */
    @JsonProperty("tx")
    public Tx tx;

    /**
     * Time of the previous block. For heights > 1, it's the weighted median of
     * the timestamps of the valid votes in the block.LastCommit. For height ==
     * 1, it's genesis time.
     */
    @JsonProperty("timestamp")
    public String timestamp;

    /**
     * Events defines all the events emitted by processing a transaction. Note,
     * these events include those emitted by processing all the messages and
     * those emitted from the ante handler. Whereas Logs contains the events,
     * with additional metadata, emitted only by processing the messages.
     */
    @JsonProperty("events")
    public List<String> events;

}
