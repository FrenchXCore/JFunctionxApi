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
 * DelegationDelegatorReward represents the properties of a delegator&#39;s delegation reward.
 */
@ApiModel(description = "DelegationDelegatorReward represents the properties of a delegator's delegation reward.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosDistributionV1beta1DelegationDelegatorReward {
  public static final String SERIALIZED_NAME_VALIDATOR_ADDRESS = "validator_address";
  @SerializedName(SERIALIZED_NAME_VALIDATOR_ADDRESS)
  private String validatorAddress;

  public static final String SERIALIZED_NAME_REWARD = "reward";
  @SerializedName(SERIALIZED_NAME_REWARD)
  private List<InlineResponse20016Pool> reward = null;


  public CosmosDistributionV1beta1DelegationDelegatorReward validatorAddress(String validatorAddress) {
    
    this.validatorAddress = validatorAddress;
    return this;
  }

   /**
   * Get validatorAddress
   * @return validatorAddress
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getValidatorAddress() {
    return validatorAddress;
  }


  public void setValidatorAddress(String validatorAddress) {
    this.validatorAddress = validatorAddress;
  }


  public CosmosDistributionV1beta1DelegationDelegatorReward reward(List<InlineResponse20016Pool> reward) {
    
    this.reward = reward;
    return this;
  }

  public CosmosDistributionV1beta1DelegationDelegatorReward addRewardItem(InlineResponse20016Pool rewardItem) {
    if (this.reward == null) {
      this.reward = new ArrayList<InlineResponse20016Pool>();
    }
    this.reward.add(rewardItem);
    return this;
  }

   /**
   * Get reward
   * @return reward
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<InlineResponse20016Pool> getReward() {
    return reward;
  }


  public void setReward(List<InlineResponse20016Pool> reward) {
    this.reward = reward;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosDistributionV1beta1DelegationDelegatorReward cosmosDistributionV1beta1DelegationDelegatorReward = (CosmosDistributionV1beta1DelegationDelegatorReward) o;
    return Objects.equals(this.validatorAddress, cosmosDistributionV1beta1DelegationDelegatorReward.validatorAddress) &&
        Objects.equals(this.reward, cosmosDistributionV1beta1DelegationDelegatorReward.reward);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validatorAddress, reward);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosDistributionV1beta1DelegationDelegatorReward {\n");
    sb.append("    validatorAddress: ").append(toIndentedString(validatorAddress)).append("\n");
    sb.append("    reward: ").append(toIndentedString(reward)).append("\n");
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

