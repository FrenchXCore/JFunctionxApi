package eu.frenchxcore.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.group.GroupAccountInfo;

/**
 * QueryGroupAccountInfoResponse is the Query/GroupAccountInfo response type.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryGroupAccountInfoResponse {

    /**
     * info is the GroupAccountInfo for the group account.
     */
    @JsonProperty("info")
    public GroupAccountInfo info;

}
