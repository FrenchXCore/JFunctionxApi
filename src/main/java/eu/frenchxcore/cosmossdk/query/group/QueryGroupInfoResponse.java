package eu.frenchxcore.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.group.GroupInfo;

/**
 * QueryGroupInfoResponse is the Query/GroupInfo response type.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryGroupInfoResponse {

    /**
     * info is the GroupInfo for the group.
     */
    @JsonProperty("info")
    public GroupInfo info;

}
