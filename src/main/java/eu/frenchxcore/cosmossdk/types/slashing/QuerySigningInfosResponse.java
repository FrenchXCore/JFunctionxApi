package eu.frenchxcore.cosmossdk.types.slashing;

import eu.frenchxcore.cosmossdk.types._base.ValidatorSigningInfo;
import eu.frenchxcore.cosmossdk.types._base.query.PageResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
