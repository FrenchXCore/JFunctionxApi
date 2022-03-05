package eu.frenchxcore.messages.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.group.GroupMember;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;

import java.util.List;

/**
 * QueryGroupMembersResponse is the Query/GroupMembersResponse response type.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryGroupMembersResponse {

    /**
     * members are the members of the group with given group_id.
     */
    @JsonProperty("members")
    public List<GroupMember> members;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
