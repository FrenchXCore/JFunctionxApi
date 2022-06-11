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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20040UnbondingResponses;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * QueryDelegationResponse is response type for the Query/UnbondingDelegation RPC method.
 */
@ApiModel(description = "QueryDelegationResponse is response type for the Query/UnbondingDelegation RPC method.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T23:37:17.045990200+02:00[Europe/Paris]")
public class CosmosStakingV1beta1QueryUnbondingDelegationResponse {
  public static final String SERIALIZED_NAME_UNBOND = "unbond";
  @SerializedName(SERIALIZED_NAME_UNBOND)
  private InlineResponse20040UnbondingResponses unbond;


  public CosmosStakingV1beta1QueryUnbondingDelegationResponse unbond(InlineResponse20040UnbondingResponses unbond) {
    
    this.unbond = unbond;
    return this;
  }

   /**
   * Get unbond
   * @return unbond
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20040UnbondingResponses getUnbond() {
    return unbond;
  }


  public void setUnbond(InlineResponse20040UnbondingResponses unbond) {
    this.unbond = unbond;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosStakingV1beta1QueryUnbondingDelegationResponse cosmosStakingV1beta1QueryUnbondingDelegationResponse = (CosmosStakingV1beta1QueryUnbondingDelegationResponse) o;
    return Objects.equals(this.unbond, cosmosStakingV1beta1QueryUnbondingDelegationResponse.unbond);
  }

  @Override
  public int hashCode() {
    return Objects.hash(unbond);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosStakingV1beta1QueryUnbondingDelegationResponse {\n");
    sb.append("    unbond: ").append(toIndentedString(unbond)).append("\n");
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

