package eu.frenchxcore.model.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.group.GroupInfo;
import eu.frenchxcore.model.cosmossdk.types.query.PageResponse;

import java.util.List;

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
