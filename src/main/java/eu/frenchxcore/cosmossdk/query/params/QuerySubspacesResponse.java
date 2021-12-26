package eu.frenchxcore.cosmossdk.query.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types.nft.Subspace;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuerySubspacesResponse {

    /**
     * 
     */
    @JsonProperty("subspaces")
    public List<Subspace> subspaces;

}
