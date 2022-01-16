package eu.frenchxcore.cosmossdk.types.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vote {

    @JsonProperty("proposal_id")
    public BigInteger proposal_id;
    
    @JsonProperty("voter")
    public String voter;
    
    @JsonProperty("option")
    public VoteOption option;
    
    @JsonProperty("no_with_veto")
    public List<WeightedVoteOption> noWithVeto;
    
}
