package eu.frenchxcore.messages.cosmossdk.types.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

/**
 * GroupAccountInfo represents the high-level on-chain information for a group account.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupAccountInfo {

    /**
     * address is the group account address.
     */
    @JsonProperty("address")
    public String address;
    
    /**
     * groupId is the unique ID of the group.
     */
    @JsonProperty("group_id")
    public BigInteger groupId;
    
    /**
     * admin is the account address of the group admin.
     */
    @JsonProperty("admin")
    public String admin;
    
    /**
     * metadata is any arbitrary metadata attached to the group account.
     */
    @JsonProperty("metadata")
    public String metadata;
    
    /**
     * version is used to track changes to a group's GroupAccountInfo structure that would create a different result on a running proposal.
     */
    @JsonProperty("version")
    public BigInteger version;
    
    /**
     * decisionPolicy specifies the group account's decision policy.
     */
    @JsonProperty("decision_policy")
    public ThresholdDecisionPolicy decisionPolicy;
    
}