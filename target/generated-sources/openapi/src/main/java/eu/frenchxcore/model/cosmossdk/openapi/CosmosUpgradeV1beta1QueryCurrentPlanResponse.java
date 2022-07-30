/*
 * Cosmos SDK - gRPC Gateway docs
 * A REST interface for state queries
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package eu.frenchxcore.model.cosmossdk.openapi;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20052Plan;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * QueryCurrentPlanResponse is the response type for the Query/CurrentPlan RPC method.
 */
@ApiModel(description = "QueryCurrentPlanResponse is the response type for the Query/CurrentPlan RPC method.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosUpgradeV1beta1QueryCurrentPlanResponse {
  public static final String SERIALIZED_NAME_PLAN = "plan";
  @SerializedName(SERIALIZED_NAME_PLAN)
  private InlineResponse20052Plan plan;


  public CosmosUpgradeV1beta1QueryCurrentPlanResponse plan(InlineResponse20052Plan plan) {
    
    this.plan = plan;
    return this;
  }

   /**
   * Get plan
   * @return plan
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20052Plan getPlan() {
    return plan;
  }


  public void setPlan(InlineResponse20052Plan plan) {
    this.plan = plan;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosUpgradeV1beta1QueryCurrentPlanResponse cosmosUpgradeV1beta1QueryCurrentPlanResponse = (CosmosUpgradeV1beta1QueryCurrentPlanResponse) o;
    return Objects.equals(this.plan, cosmosUpgradeV1beta1QueryCurrentPlanResponse.plan);
  }

  @Override
  public int hashCode() {
    return Objects.hash(plan);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosUpgradeV1beta1QueryCurrentPlanResponse {\n");
    sb.append("    plan: ").append(toIndentedString(plan)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

