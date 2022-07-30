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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20023Slashes;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse200Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * QueryValidatorSlashesResponse is the response type for the Query/ValidatorSlashes RPC method.
 */
@ApiModel(description = "QueryValidatorSlashesResponse is the response type for the Query/ValidatorSlashes RPC method.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosDistributionV1beta1QueryValidatorSlashesResponse {
  public static final String SERIALIZED_NAME_SLASHES = "slashes";
  @SerializedName(SERIALIZED_NAME_SLASHES)
  private List<InlineResponse20023Slashes> slashes = null;

  public static final String SERIALIZED_NAME_PAGINATION = "pagination";
  @SerializedName(SERIALIZED_NAME_PAGINATION)
  private InlineResponse200Pagination pagination;


  public CosmosDistributionV1beta1QueryValidatorSlashesResponse slashes(List<InlineResponse20023Slashes> slashes) {
    
    this.slashes = slashes;
    return this;
  }

  public CosmosDistributionV1beta1QueryValidatorSlashesResponse addSlashesItem(InlineResponse20023Slashes slashesItem) {
    if (this.slashes == null) {
      this.slashes = new ArrayList<InlineResponse20023Slashes>();
    }
    this.slashes.add(slashesItem);
    return this;
  }

   /**
   * slashes defines the slashes the validator received.
   * @return slashes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "slashes defines the slashes the validator received.")

  public List<InlineResponse20023Slashes> getSlashes() {
    return slashes;
  }


  public void setSlashes(List<InlineResponse20023Slashes> slashes) {
    this.slashes = slashes;
  }


  public CosmosDistributionV1beta1QueryValidatorSlashesResponse pagination(InlineResponse200Pagination pagination) {
    
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
    CosmosDistributionV1beta1QueryValidatorSlashesResponse cosmosDistributionV1beta1QueryValidatorSlashesResponse = (CosmosDistributionV1beta1QueryValidatorSlashesResponse) o;
    return Objects.equals(this.slashes, cosmosDistributionV1beta1QueryValidatorSlashesResponse.slashes) &&
        Objects.equals(this.pagination, cosmosDistributionV1beta1QueryValidatorSlashesResponse.pagination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(slashes, pagination);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosDistributionV1beta1QueryValidatorSlashesResponse {\n");
    sb.append("    slashes: ").append(toIndentedString(slashes)).append("\n");
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

