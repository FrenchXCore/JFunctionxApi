package eu.frenchxcore.model.cosmossdk.query.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.params.v1beta1.Subspace;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuerySubspacesResponse {

    /**
     * 
     */
    @JsonProperty("subspaces")
    public List<Subspace> subspaces;

}
