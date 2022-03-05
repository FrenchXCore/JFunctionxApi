package eu.frenchxcore.messages.cosmossdk.query.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.messages.cosmossdk.types.distribution.ValidatorSlashEvent;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryValidatorSlashesResponse {
    
    /**
     * slashes defines the slashes the validator received.
     */
    @JsonProperty("slashes")
    public List<ValidatorSlashEvent> slashes;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}