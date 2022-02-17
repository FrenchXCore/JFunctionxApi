package eu.frenchxcore.cosmossdk.query.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.gov.v1beta1.Proposal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryProposalResponse {

    /**
     * 
     */
    @JsonProperty("proposal")
    public Proposal proposal;

}
