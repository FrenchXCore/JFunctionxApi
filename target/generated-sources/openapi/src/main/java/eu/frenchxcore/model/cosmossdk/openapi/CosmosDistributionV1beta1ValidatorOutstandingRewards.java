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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ValidatorOutstandingRewards represents outstanding (un-withdrawn) rewards for a validator inexpensive to track, allows simple sanity checks.
 */
@ApiModel(description = "ValidatorOutstandingRewards represents outstanding (un-withdrawn) rewards for a validator inexpensive to track, allows simple sanity checks.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosDistributionV1beta1ValidatorOutstandingRewards {
  public static final String SERIALIZED_NAME_REWARDS = "rewards";
  @SerializedName(SERIALIZED_NAME_REWARDS)
  private List<InlineResponse20016Pool> rewards = null;


  public CosmosDistributionV1beta1ValidatorOutstandingRewards rewards(List<InlineResponse20016Pool> rewards) {
    
    this.rewards = rewards;
    return this;
  }

  public CosmosDistributionV1beta1ValidatorOutstandingRewards addRewardsItem(InlineResponse20016Pool rewardsItem) {
    if (this.rewards == null) {
      this.rewards = new ArrayList<InlineResponse20016Pool>();
    }
    this.rewards.add(rewardsItem);
    return this;
  }

   /**
   * Get rewards
   * @return rewards
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<InlineResponse20016Pool> getRewards() {
    return rewards;
  }


  public void setRewards(List<InlineResponse20016Pool> rewards) {
    this.rewards = rewards;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosDistributionV1beta1ValidatorOutstandingRewards cosmosDistributionV1beta1ValidatorOutstandingRewards = (CosmosDistributionV1beta1ValidatorOutstandingRewards) o;
    return Objects.equals(this.rewards, cosmosDistributionV1beta1ValidatorOutstandingRewards.rewards);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rewards);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosDistributionV1beta1ValidatorOutstandingRewards {\n");
    sb.append("    rewards: ").append(toIndentedString(rewards)).append("\n");
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

