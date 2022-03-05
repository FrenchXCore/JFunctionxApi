package eu.frenchxcore.messages.cosmossdk.query.slashing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;
import eu.frenchxcore.messages.cosmossdk.types.slashing.ValidatorSigningInfo;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuerySigningInfosResponse {

    /**
     * info is the signing info of all validators
     */
    @JsonProperty("info")
    public List<ValidatorSigningInfo> infos;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
