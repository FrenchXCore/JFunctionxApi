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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse2003Balances;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * DenomOwner defines structure representing an account that owns or holds a particular denominated token. It contains the account address and account balance of the denominated token.
 */
@ApiModel(description = "DenomOwner defines structure representing an account that owns or holds a particular denominated token. It contains the account address and account balance of the denominated token.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class InlineResponse2005DenomOwners {
  public static final String SERIALIZED_NAME_ADDRESS = "address";
  @SerializedName(SERIALIZED_NAME_ADDRESS)
  private String address;

  public static final String SERIALIZED_NAME_BALANCE = "balance";
  @SerializedName(SERIALIZED_NAME_BALANCE)
  private InlineResponse2003Balances balance;


  public InlineResponse2005DenomOwners address(String address) {
    
    this.address = address;
    return this;
  }

   /**
   * address defines the address that owns a particular denomination.
   * @return address
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "address defines the address that owns a particular denomination.")

  public String getAddress() {
    return address;
  }


  public void setAddress(String address) {
    this.address = address;
  }


  public InlineResponse2005DenomOwners balance(InlineResponse2003Balances balance) {
    
    this.balance = balance;
    return this;
  }

   /**
   * Get balance
   * @return balance
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse2003Balances getBalance() {
    return balance;
  }


  public void setBalance(InlineResponse2003Balances balance) {
    this.balance = balance;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse2005DenomOwners inlineResponse2005DenomOwners = (InlineResponse2005DenomOwners) o;
    return Objects.equals(this.address, inlineResponse2005DenomOwners.address) &&
        Objects.equals(this.balance, inlineResponse2005DenomOwners.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2005DenomOwners {\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
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

