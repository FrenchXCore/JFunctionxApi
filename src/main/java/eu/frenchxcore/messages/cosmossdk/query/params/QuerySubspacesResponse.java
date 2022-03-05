package eu.frenchxcore.messages.cosmossdk.query.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.messages.cosmossdk.types.params.v1beta1.Subspace;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuerySubspacesResponse {

    /**
     * 
     */
    @JsonProperty("subspaces")
    public List<Subspace> subspaces;

}
