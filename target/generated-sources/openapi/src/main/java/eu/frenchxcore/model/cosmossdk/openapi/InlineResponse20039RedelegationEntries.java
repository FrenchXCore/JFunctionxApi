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
import org.threeten.bp.OffsetDateTime;

/**
 * RedelegationEntry defines a redelegation object with relevant metadata.
 */
@ApiModel(description = "RedelegationEntry defines a redelegation object with relevant metadata.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class InlineResponse20039RedelegationEntries {
  public static final String SERIALIZED_NAME_CREATION_HEIGHT = "creation_height";
  @SerializedName(SERIALIZED_NAME_CREATION_HEIGHT)
  private String creationHeight;

  public static final String SERIALIZED_NAME_COMPLETION_TIME = "completion_time";
  @SerializedName(SERIALIZED_NAME_COMPLETION_TIME)
  private OffsetDateTime completionTime;

  public static final String SERIALIZED_NAME_INITIAL_BALANCE = "initial_balance";
  @SerializedName(SERIALIZED_NAME_INITIAL_BALANCE)
  private String initialBalance;

  public static final String SERIALIZED_NAME_SHARES_DST = "shares_dst";
  @SerializedName(SERIALIZED_NAME_SHARES_DST)
  private String sharesDst;


  public InlineResponse20039RedelegationEntries creationHeight(String creationHeight) {
    
    this.creationHeight = creationHeight;
    return this;
  }

   /**
   * creation_height  defines the height which the redelegation took place.
   * @return creationHeight
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "creation_height  defines the height which the redelegation took place.")

  public String getCreationHeight() {
    return creationHeight;
  }


  public void setCreationHeight(String creationHeight) {
    this.creationHeight = creationHeight;
  }


  public InlineResponse20039RedelegationEntries completionTime(OffsetDateTime completionTime) {
    
    this.completionTime = completionTime;
    return this;
  }

   /**
   * completion_time defines the unix time for redelegation completion.
   * @return completionTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "completion_time defines the unix time for redelegation completion.")

  public OffsetDateTime getCompletionTime() {
    return completionTime;
  }


  public void setCompletionTime(OffsetDateTime completionTime) {
    this.completionTime = completionTime;
  }


  public InlineResponse20039RedelegationEntries initialBalance(String initialBalance) {
    
    this.initialBalance = initialBalance;
    return this;
  }

   /**
   * initial_balance defines the initial balance when redelegation started.
   * @return initialBalance
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "initial_balance defines the initial balance when redelegation started.")

  public String getInitialBalance() {
    return initialBalance;
  }


  public void setInitialBalance(String initialBalance) {
    this.initialBalance = initialBalance;
  }


  public InlineResponse20039RedelegationEntries sharesDst(String sharesDst) {
    
    this.sharesDst = sharesDst;
    return this;
  }

   /**
   * shares_dst is the amount of destination-validator shares created by redelegation.
   * @return sharesDst
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "shares_dst is the amount of destination-validator shares created by redelegation.")

  public String getSharesDst() {
    return sharesDst;
  }


  public void setSharesDst(String sharesDst) {
    this.sharesDst = sharesDst;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse20039RedelegationEntries inlineResponse20039RedelegationEntries = (InlineResponse20039RedelegationEntries) o;
    return Objects.equals(this.creationHeight, inlineResponse20039RedelegationEntries.creationHeight) &&
        Objects.equals(this.completionTime, inlineResponse20039RedelegationEntries.completionTime) &&
        Objects.equals(this.initialBalance, inlineResponse20039RedelegationEntries.initialBalance) &&
        Objects.equals(this.sharesDst, inlineResponse20039RedelegationEntries.sharesDst);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creationHeight, completionTime, initialBalance, sharesDst);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse20039RedelegationEntries {\n");
    sb.append("    creationHeight: ").append(toIndentedString(creationHeight)).append("\n");
    sb.append("    completionTime: ").append(toIndentedString(completionTime)).append("\n");
    sb.append("    initialBalance: ").append(toIndentedString(initialBalance)).append("\n");
    sb.append("    sharesDst: ").append(toIndentedString(sharesDst)).append("\n");
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

