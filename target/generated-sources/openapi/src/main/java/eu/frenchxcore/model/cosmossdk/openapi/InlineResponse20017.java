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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20016Pool;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20017Rewards;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * QueryDelegationTotalRewardsResponse is the response type for the Query/DelegationTotalRewards RPC method.
 */
@ApiModel(description = "QueryDelegationTotalRewardsResponse is the response type for the Query/DelegationTotalRewards RPC method.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class InlineResponse20017 {
  public static final String SERIALIZED_NAME_REWARDS = "rewards";
  @SerializedName(SERIALIZED_NAME_REWARDS)
  private List<InlineResponse20017Rewards> rewards = null;

  public static final String SERIALIZED_NAME_TOTAL = "total";
  @SerializedName(SERIALIZED_NAME_TOTAL)
  private List<InlineResponse20016Pool> total = null;


  public InlineResponse20017 rewards(List<InlineResponse20017Rewards> rewards) {
    
    this.rewards = rewards;
    return this;
  }

  public InlineResponse20017 addRewardsItem(InlineResponse20017Rewards rewardsItem) {
    if (this.rewards == null) {
      this.rewards = new ArrayList<InlineResponse20017Rewards>();
    }
    this.rewards.add(rewardsItem);
    return this;
  }

   /**
   * rewards defines all the rewards accrued by a delegator.
   * @return rewards
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "rewards defines all the rewards accrued by a delegator.")

  public List<InlineResponse20017Rewards> getRewards() {
    return rewards;
  }


  public void setRewards(List<InlineResponse20017Rewards> rewards) {
    this.rewards = rewards;
  }


  public InlineResponse20017 total(List<InlineResponse20016Pool> total) {
    
    this.total = total;
    return this;
  }

  public InlineResponse20017 addTotalItem(InlineResponse20016Pool totalItem) {
    if (this.total == null) {
      this.total = new ArrayList<InlineResponse20016Pool>();
    }
    this.total.add(totalItem);
    return this;
  }

   /**
   * total defines the sum of all the rewards.
   * @return total
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "total defines the sum of all the rewards.")

  public List<InlineResponse20016Pool> getTotal() {
    return total;
  }


  public void setTotal(List<InlineResponse20016Pool> total) {
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
    InlineResponse20017 inlineResponse20017 = (InlineResponse20017) o;
    return Objects.equals(this.rewards, inlineResponse20017.rewards) &&
        Objects.equals(this.total, inlineResponse20017.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rewards, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse20017 {\n");
    sb.append("    rewards: ").append(toIndentedString(rewards)).append("\n");
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

