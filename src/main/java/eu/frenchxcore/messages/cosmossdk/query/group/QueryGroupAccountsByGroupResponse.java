package eu.frenchxcore.messages.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.group.GroupAccountInfo;
import java.util.List;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;

/**
 * QueryGroupAccountsByGroupResponse is the Query/GroupAccountsByGroup response type.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryGroupAccountsByGroupResponse {

    /**
     * group_accounts are the group accounts info associated with the provided group.
     */
    @JsonProperty("group_accounts")
    public List<GroupAccountInfo> groupAccounts;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
