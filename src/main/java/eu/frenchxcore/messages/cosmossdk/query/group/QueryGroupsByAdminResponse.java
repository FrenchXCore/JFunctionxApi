package eu.frenchxcore.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.group.GroupInfo;
import java.util.List;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;

/**
 * QueryGroupsByAdminResponse is the Query/GroupsByAdminResponse response type.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryGroupsByAdminResponse {

    /**
     * groups are the groups info with the provided admin.
     */
    @JsonProperty("groups")
    public List<GroupInfo> groups;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
