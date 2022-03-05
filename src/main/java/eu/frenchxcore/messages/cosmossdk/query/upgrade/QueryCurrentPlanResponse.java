package eu.frenchxcore.messages.cosmossdk.query.upgrade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.upgrade.v1beta1.Plan;

/**
 * QueryCurrentPlanResponse is the response type for the Query/CurrentPlan RPC method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryCurrentPlanResponse {

    /**
     * plan is the current upgrade plan.
     */
    @JsonProperty("plan")
    public Plan plan;
    
}