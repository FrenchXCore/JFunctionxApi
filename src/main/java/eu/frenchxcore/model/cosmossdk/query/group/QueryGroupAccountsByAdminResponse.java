package eu.frenchxcore.model.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.group.GroupAccountInfo;
import eu.frenchxcore.model.cosmossdk.types.query.PageResponse;

import java.util.List;

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
