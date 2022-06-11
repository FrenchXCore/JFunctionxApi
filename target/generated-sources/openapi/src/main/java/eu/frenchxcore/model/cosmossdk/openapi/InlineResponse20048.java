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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse200Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * QueryValidatorUnbondingDelegationsResponse is response type for the Query/ValidatorUnbondingDelegations RPC method.
 */
@ApiModel(description = "QueryValidatorUnbondingDelegationsResponse is response type for the Query/ValidatorUnbondingDelegations RPC method.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T23:37:17.045990200+02:00[Europe/Paris]")
public class InlineResponse20048 {
  public static final String SERIALIZED_NAME_UNBONDING_RESPONSES = "unbonding_responses";
  @SerializedName(SERIALIZED_NAME_UNBONDING_RESPONSES)
  private List<InlineResponse20040UnbondingResponses> unbondingResponses = null;

  public static final String SERIALIZED_NAME_PAGINATION = "pagination";
  @SerializedName(SERIALIZED_NAME_PAGINATION)
  private InlineResponse200Pagination pagination;


  public InlineResponse20048 unbondingResponses(List<InlineResponse20040UnbondingResponses> unbondingResponses) {
    
    this.unbondingResponses = unbondingResponses;
    return this;
  }

  public InlineResponse20048 addUnbondingResponsesItem(InlineResponse20040UnbondingResponses unbondingResponsesItem) {
    if (this.unbondingResponses == null) {
      this.unbondingResponses = new ArrayList<InlineResponse20040UnbondingResponses>();
    }
    this.unbondingResponses.add(unbondingResponsesItem);
    return this;
  }

   /**
   * Get unbondingResponses
   * @return unbondingResponses
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<InlineResponse20040UnbondingResponses> getUnbondingResponses() {
    return unbondingResponses;
  }


  public void setUnbondingResponses(List<InlineResponse20040UnbondingResponses> unbondingResponses) {
    this.unbondingResponses = unbondingResponses;
  }


  public InlineResponse20048 pagination(InlineResponse200Pagination pagination) {
    
    this.pagination = pagination;
    return this;
  }

   /**
   * Get pagination
   * @return pagination
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse200Pagination getPagination() {
    return pagination;
  }


  public void setPagination(InlineResponse200Pagination pagination) {
    this.pagination = pagination;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse20048 inlineResponse20048 = (InlineResponse20048) o;
    return Objects.equals(this.unbondingResponses, inlineResponse20048.unbondingResponses) &&
        Objects.equals(this.pagination, inlineResponse20048.pagination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(unbondingResponses, pagination);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse20048 {\n");
    sb.append("    unbondingResponses: ").append(toIndentedString(unbondingResponses)).append("\n");
    sb.append("    pagination: ").append(toIndentedString(pagination)).append("\n");
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

