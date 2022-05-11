package eu.frenchxcore.model.cosmossdk.types.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageResponse {

    /**
     * next_key is the key to be passed to PageRequest.key to query the next page most efficiently
     */
    @JsonProperty("next_key")
    public String next_key;

    /**
     * total is total number of results available if PageRequest.count_total was set, its value is undefined otherwise.
     */
    @JsonProperty("total")
    public String total;

}
