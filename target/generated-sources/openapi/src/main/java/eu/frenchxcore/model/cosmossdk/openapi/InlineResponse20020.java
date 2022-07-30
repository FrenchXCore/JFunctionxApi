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
 * QueryDelegatorWithdrawAddressResponse is the response type for the Query/DelegatorWithdrawAddress RPC method.
 */
@ApiModel(description = "QueryDelegatorWithdrawAddressResponse is the response type for the Query/DelegatorWithdrawAddress RPC method.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class InlineResponse20020 {
  public static final String SERIALIZED_NAME_WITHDRAW_ADDRESS = "withdraw_address";
  @SerializedName(SERIALIZED_NAME_WITHDRAW_ADDRESS)
  private String withdrawAddress;


  public InlineResponse20020 withdrawAddress(String withdrawAddress) {
    
    this.withdrawAddress = withdrawAddress;
    return this;
  }

   /**
   * withdraw_address defines the delegator address to query for.
   * @return withdrawAddress
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "withdraw_address defines the delegator address to query for.")

  public String getWithdrawAddress() {
    return withdrawAddress;
  }


  public void setWithdrawAddress(String withdrawAddress) {
    this.withdrawAddress = withdrawAddress;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse20020 inlineResponse20020 = (InlineResponse20020) o;
    return Objects.equals(this.withdrawAddress, inlineResponse20020.withdrawAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(withdrawAddress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse20020 {\n");
    sb.append("    withdrawAddress: ").append(toIndentedString(withdrawAddress)).append("\n");
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

