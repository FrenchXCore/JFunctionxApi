package eu.frenchxcore.model.cosmossdk.types.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Members defines a repeated slice of Member objects.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Members {

    /**
     * members is the list of members.
     */
    @JsonProperty("members")
    public List<Member> members;
    
}