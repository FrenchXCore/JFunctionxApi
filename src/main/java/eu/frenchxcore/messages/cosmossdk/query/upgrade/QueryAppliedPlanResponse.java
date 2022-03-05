package eu.frenchxcore.messages.cosmossdk.query.upgrade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

/**
 * QueryAppliedPlanResponse is the response type for the Query/AppliedPlan RPC method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAppliedPlanResponse {

    /**
     * height is the block height at which the plan was applied.
     */
    @JsonProperty("height")
    public BigInteger height;
    
}