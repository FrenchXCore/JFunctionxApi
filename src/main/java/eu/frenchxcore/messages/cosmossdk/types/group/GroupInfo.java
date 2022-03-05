package eu.frenchxcore.cosmossdk.types.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

/**
 * GroupInfo represents the high-level on-chain information for a group.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupInfo {

    /**
     * groupId is the unique ID of the group.
     */
    @JsonProperty("group_id")
    public BigInteger groupId;
    
    /**
     * admin is the account address of the group's admin.
     */
    @JsonProperty("admin")
    public String admin;
    
    /**
     * metadata is any arbitrary metadata attached to the group account.
     */
    @JsonProperty("metadata")
    public String metadata;
    
    /**
     * version is used to track changes to a group's membership structure that would break existing proposals. Whenever any members weight is changed, or any member is added or removed this version is incremented and will cause proposals based on older versions of this group to fail.
     */
    @JsonProperty("version")
    public BigInteger version;
    
    /**
     * totalWeight is the sum of the group members' weights.
     */
    @JsonProperty("total_weight")
    public String totalWeight;
    
}