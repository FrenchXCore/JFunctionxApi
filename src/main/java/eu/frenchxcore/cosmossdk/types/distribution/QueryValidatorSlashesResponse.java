package eu.frenchxcore.cosmossdk.types.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types._base.ValidatorSlashEvent;
import eu.frenchxcore.cosmossdk.types._base.query.PageResponse;

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