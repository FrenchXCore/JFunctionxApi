package eu.frenchxcore.messages.cosmossdk.types.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Member represents a group member with an account address, non-zero weight and metadata.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {

    /**
     * address is the member's account address.
     */
    @JsonProperty("address")
    public String address;
    
    /**
     * weight is the member's voting weight that should be greater than 0.
     */
    @JsonProperty("weight")
    public String weight;
    
    /**
     * metadata is any arbitrary metadata to attached to the member.
     */
    @JsonProperty("metadata")
    public String metadata;
    
}