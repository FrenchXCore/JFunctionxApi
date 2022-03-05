package eu.frenchxcore.messages.cosmossdk.types.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * PageRequest is to be embedded in gRPC request messages for efficient pagination.
 * Ex: message SomeRequest { Foo some_parameter = 1; PageRequest pagination = 2; }
 */
public class PageRequest {

    /**
     * key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     * Only one of offset or key should be set.
     */
    @JsonProperty("key")
    public String key;

    /**
     * offset is a numeric offset that can be used when key is unavailable.
     * It is less efficient than using key. Only one of offset or key should be set.
     */
    @JsonProperty("offset")
    public BigInteger offset;

    /**
     * limit is the total number of results to be returned in the result page.
     * If left empty it will default to a value to be set by each app.
     */
    @JsonProperty("limit")
    public BigInteger limit;

    /**
     * count_total is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs.
     * count_total is only respected when offset is used. It is ignored when key is set.
     */
    @JsonProperty("count_total")
    public Boolean count_total;

    /**
     * reverse is set to true if results are to be returned in the descending order.
     */
    @JsonProperty("reverse")
    public Boolean reverse;

}