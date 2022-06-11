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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20039RedelegationEntries;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * RedelegationEntryResponse is equivalent to a RedelegationEntry except that it contains a balance in addition to shares which is more suitable for client responses.
 */
@ApiModel(description = "RedelegationEntryResponse is equivalent to a RedelegationEntry except that it contains a balance in addition to shares which is more suitable for client responses.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T23:37:17.045990200+02:00[Europe/Paris]")
public class InlineResponse20039Entries {
  public static final String SERIALIZED_NAME_REDELEGATION_ENTRY = "redelegation_entry";
  @SerializedName(SERIALIZED_NAME_REDELEGATION_ENTRY)
  private InlineResponse20039RedelegationEntries redelegationEntry;

  public static final String SERIALIZED_NAME_BALANCE = "balance";
  @SerializedName(SERIALIZED_NAME_BALANCE)
  private String balance;


  public InlineResponse20039Entries redelegationEntry(InlineResponse20039RedelegationEntries redelegationEntry) {
    
    this.redelegationEntry = redelegationEntry;
    return this;
  }

   /**
   * Get redelegationEntry
   * @return redelegationEntry
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20039RedelegationEntries getRedelegationEntry() {
    return redelegationEntry;
  }


  public void setRedelegationEntry(InlineResponse20039RedelegationEntries redelegationEntry) {
    this.redelegationEntry = redelegationEntry;
  }


  public InlineResponse20039Entries balance(String balance) {
    
    this.balance = balance;
    return this;
  }

   /**
   * Get balance
   * @return balance
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getBalance() {
    return balance;
  }


  public void setBalance(String balance) {
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
    InlineResponse20039Entries inlineResponse20039Entries = (InlineResponse20039Entries) o;
    return Objects.equals(this.redelegationEntry, inlineResponse20039Entries.redelegationEntry) &&
        Objects.equals(this.balance, inlineResponse20039Entries.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(redelegationEntry, balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse20039Entries {\n");
    sb.append("    redelegationEntry: ").append(toIndentedString(redelegationEntry)).append("\n");
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

