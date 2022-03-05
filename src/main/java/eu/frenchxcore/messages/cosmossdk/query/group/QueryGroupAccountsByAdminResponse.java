package eu.frenchxcore.messages.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.group.GroupAccountInfo;
import java.util.List;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;

/**
 * QueryGroupAccountsByAdminResponse is the Query/GroupAccountsByAdmin response type.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryGroupAccountsByAdminResponse {

    /**
     * group_accounts are the group accounts info with provided admin.
     */
    @JsonProperty("group_accounts")
    public List<GroupAccountInfo> groupAccounts;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
