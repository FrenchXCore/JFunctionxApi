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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20038DelegationResponses;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * QueryDelegationResponse is response type for the Query/Delegation RPC method.
 */
@ApiModel(description = "QueryDelegationResponse is response type for the Query/Delegation RPC method.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosStakingV1beta1QueryDelegationResponse {
  public static final String SERIALIZED_NAME_DELEGATION_RESPONSE = "delegation_response";
  @SerializedName(SERIALIZED_NAME_DELEGATION_RESPONSE)
  private InlineResponse20038DelegationResponses delegationResponse;


  public CosmosStakingV1beta1QueryDelegationResponse delegationResponse(InlineResponse20038DelegationResponses delegationResponse) {
    
    this.delegationResponse = delegationResponse;
    return this;
  }

   /**
   * Get delegationResponse
   * @return delegationResponse
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20038DelegationResponses getDelegationResponse() {
    return delegationResponse;
  }


  public void setDelegationResponse(InlineResponse20038DelegationResponses delegationResponse) {
    this.delegationResponse = delegationResponse;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosStakingV1beta1QueryDelegationResponse cosmosStakingV1beta1QueryDelegationResponse = (CosmosStakingV1beta1QueryDelegationResponse) o;
    return Objects.equals(this.delegationResponse, cosmosStakingV1beta1QueryDelegationResponse.delegationResponse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(delegationResponse);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosStakingV1beta1QueryDelegationResponse {\n");
    sb.append("    delegationResponse: ").append(toIndentedString(delegationResponse)).append("\n");
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

