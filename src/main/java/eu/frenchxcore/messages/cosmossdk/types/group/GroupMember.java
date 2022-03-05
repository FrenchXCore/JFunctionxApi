package eu.frenchxcore.messages.cosmossdk.types.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

/**
 * GroupMember represents the relationship between a group and a member.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupMember {

    /**
     * groupId is the unique ID of the group.
     */
    @JsonProperty("group_id")
    public BigInteger groupId;
    
    /**
     * member is the member data.
     */
    @JsonProperty("member")
    public Member member;
    
}