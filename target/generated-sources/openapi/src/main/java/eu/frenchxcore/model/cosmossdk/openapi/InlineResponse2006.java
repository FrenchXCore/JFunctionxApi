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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse2006Metadatas;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse200Pagination;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * QueryDenomsMetadataResponse is the response type for the Query/DenomsMetadata RPC method.
 */
@ApiModel(description = "QueryDenomsMetadataResponse is the response type for the Query/DenomsMetadata RPC method.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class InlineResponse2006 {
  public static final String SERIALIZED_NAME_METADATAS = "metadatas";
  @SerializedName(SERIALIZED_NAME_METADATAS)
  private List<InlineResponse2006Metadatas> metadatas = null;

  public static final String SERIALIZED_NAME_PAGINATION = "pagination";
  @SerializedName(SERIALIZED_NAME_PAGINATION)
  private InlineResponse200Pagination pagination;


  public InlineResponse2006 metadatas(List<InlineResponse2006Metadatas> metadatas) {
    
    this.metadatas = metadatas;
    return this;
  }

  public InlineResponse2006 addMetadatasItem(InlineResponse2006Metadatas metadatasItem) {
    if (this.metadatas == null) {
      this.metadatas = new ArrayList<InlineResponse2006Metadatas>();
    }
    this.metadatas.add(metadatasItem);
    return this;
  }

   /**
   * metadata provides the client information for all the registered tokens.
   * @return metadatas
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "metadata provides the client information for all the registered tokens.")

  public List<InlineResponse2006Metadatas> getMetadatas() {
    return metadatas;
  }


  public void setMetadatas(List<InlineResponse2006Metadatas> metadatas) {
    this.metadatas = metadatas;
  }


  public InlineResponse2006 pagination(InlineResponse200Pagination pagination) {
    
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
    InlineResponse2006 inlineResponse2006 = (InlineResponse2006) o;
    return Objects.equals(this.metadatas, inlineResponse2006.metadatas) &&
        Objects.equals(this.pagination, inlineResponse2006.pagination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(metadatas, pagination);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2006 {\n");
    sb.append("    metadatas: ").append(toIndentedString(metadatas)).append("\n");
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

