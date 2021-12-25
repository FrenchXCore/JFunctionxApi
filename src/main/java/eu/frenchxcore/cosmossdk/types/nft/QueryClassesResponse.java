package eu.frenchxcore.cosmossdk.types.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types._base.NftClass;
import eu.frenchxcore.cosmossdk.types._base.query.PageResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryClassesResponse {

    @JsonProperty("classes")
    public List<NftClass> classes;

    @JsonProperty("pagination")
    public PageResponse pagination;

}
