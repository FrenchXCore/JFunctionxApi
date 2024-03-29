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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * pagination defines an pagination for the response.
 */
@ApiModel(description = "pagination defines an pagination for the response.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class InlineResponse20014Pagination {
  public static final String SERIALIZED_NAME_NEXT_KEY = "next_key";
  @SerializedName(SERIALIZED_NAME_NEXT_KEY)
  private byte[] nextKey;

  public static final String SERIALIZED_NAME_TOTAL = "total";
  @SerializedName(SERIALIZED_NAME_TOTAL)
  private String total;


  public InlineResponse20014Pagination nextKey(byte[] nextKey) {
    
    this.nextKey = nextKey;
    return this;
  }

   /**
   * Get nextKey
   * @return nextKey
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public byte[] getNextKey() {
    return nextKey;
  }


  public void setNextKey(byte[] nextKey) {
    this.nextKey = nextKey;
  }


  public InlineResponse20014Pagination total(String total) {
    
    this.total = total;
    return this;
  }

   /**
   * Get total
   * @return total
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getTotal() {
    return total;
  }


  public void setTotal(String total) {
    this.total = total;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse20014Pagination inlineResponse20014Pagination = (InlineResponse20014Pagination) o;
    return Arrays.equals(this.nextKey, inlineResponse20014Pagination.nextKey) &&
        Objects.equals(this.total, inlineResponse20014Pagination.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Arrays.hashCode(nextKey), total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse20014Pagination {\n");
    sb.append("    nextKey: ").append(toIndentedString(nextKey)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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

