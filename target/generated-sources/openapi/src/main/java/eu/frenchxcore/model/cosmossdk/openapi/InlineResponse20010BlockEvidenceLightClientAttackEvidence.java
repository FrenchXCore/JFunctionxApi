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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20010BlockEvidenceLightClientAttackEvidenceConflictingBlock;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20010BlockEvidenceLightClientAttackEvidenceConflictingBlockValidatorSetValidators;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;

/**
 * LightClientAttackEvidence contains evidence of a set of validators attempting to mislead a light client.
 */
@ApiModel(description = "LightClientAttackEvidence contains evidence of a set of validators attempting to mislead a light client.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class InlineResponse20010BlockEvidenceLightClientAttackEvidence {
  public static final String SERIALIZED_NAME_CONFLICTING_BLOCK = "conflicting_block";
  @SerializedName(SERIALIZED_NAME_CONFLICTING_BLOCK)
  private InlineResponse20010BlockEvidenceLightClientAttackEvidenceConflictingBlock conflictingBlock;

  public static final String SERIALIZED_NAME_COMMON_HEIGHT = "common_height";
  @SerializedName(SERIALIZED_NAME_COMMON_HEIGHT)
  private String commonHeight;

  public static final String SERIALIZED_NAME_BYZANTINE_VALIDATORS = "byzantine_validators";
  @SerializedName(SERIALIZED_NAME_BYZANTINE_VALIDATORS)
  private List<InlineResponse20010BlockEvidenceLightClientAttackEvidenceConflictingBlockValidatorSetValidators> byzantineValidators = null;

  public static final String SERIALIZED_NAME_TOTAL_VOTING_POWER = "total_voting_power";
  @SerializedName(SERIALIZED_NAME_TOTAL_VOTING_POWER)
  private String totalVotingPower;

  public static final String SERIALIZED_NAME_TIMESTAMP = "timestamp";
  @SerializedName(SERIALIZED_NAME_TIMESTAMP)
  private OffsetDateTime timestamp;


  public InlineResponse20010BlockEvidenceLightClientAttackEvidence conflictingBlock(InlineResponse20010BlockEvidenceLightClientAttackEvidenceConflictingBlock conflictingBlock) {
    
    this.conflictingBlock = conflictingBlock;
    return this;
  }

   /**
   * Get conflictingBlock
   * @return conflictingBlock
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20010BlockEvidenceLightClientAttackEvidenceConflictingBlock getConflictingBlock() {
    return conflictingBlock;
  }


  public void setConflictingBlock(InlineResponse20010BlockEvidenceLightClientAttackEvidenceConflictingBlock conflictingBlock) {
    this.conflictingBlock = conflictingBlock;
  }


  public InlineResponse20010BlockEvidenceLightClientAttackEvidence commonHeight(String commonHeight) {
    
    this.commonHeight = commonHeight;
    return this;
  }

   /**
   * Get commonHeight
   * @return commonHeight
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getCommonHeight() {
    return commonHeight;
  }


  public void setCommonHeight(String commonHeight) {
    this.commonHeight = commonHeight;
  }


  public InlineResponse20010BlockEvidenceLightClientAttackEvidence byzantineValidators(List<InlineResponse20010BlockEvidenceLightClientAttackEvidenceConflictingBlockValidatorSetValidators> byzantineValidators) {
    
    this.byzantineValidators = byzantineValidators;
    return this;
  }

  public InlineResponse20010BlockEvidenceLightClientAttackEvidence addByzantineValidatorsItem(InlineResponse20010BlockEvidenceLightClientAttackEvidenceConflictingBlockValidatorSetValidators byzantineValidatorsItem) {
    if (this.byzantineValidators == null) {
      this.byzantineValidators = new ArrayList<InlineResponse20010BlockEvidenceLightClientAttackEvidenceConflictingBlockValidatorSetValidators>();
    }
    this.byzantineValidators.add(byzantineValidatorsItem);
    return this;
  }

   /**
   * Get byzantineValidators
   * @return byzantineValidators
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<InlineResponse20010BlockEvidenceLightClientAttackEvidenceConflictingBlockValidatorSetValidators> getByzantineValidators() {
    return byzantineValidators;
  }


  public void setByzantineValidators(List<InlineResponse20010BlockEvidenceLightClientAttackEvidenceConflictingBlockValidatorSetValidators> byzantineValidators) {
    this.byzantineValidators = byzantineValidators;
  }


  public InlineResponse20010BlockEvidenceLightClientAttackEvidence totalVotingPower(String totalVotingPower) {
    
    this.totalVotingPower = totalVotingPower;
    return this;
  }

   /**
   * Get totalVotingPower
   * @return totalVotingPower
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getTotalVotingPower() {
    return totalVotingPower;
  }


  public void setTotalVotingPower(String totalVotingPower) {
    this.totalVotingPower = totalVotingPower;
  }


  public InlineResponse20010BlockEvidenceLightClientAttackEvidence timestamp(OffsetDateTime timestamp) {
    
    this.timestamp = timestamp;
    return this;
  }

   /**
   * Get timestamp
   * @return timestamp
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public OffsetDateTime getTimestamp() {
    return timestamp;
  }


  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse20010BlockEvidenceLightClientAttackEvidence inlineResponse20010BlockEvidenceLightClientAttackEvidence = (InlineResponse20010BlockEvidenceLightClientAttackEvidence) o;
    return Objects.equals(this.conflictingBlock, inlineResponse20010BlockEvidenceLightClientAttackEvidence.conflictingBlock) &&
        Objects.equals(this.commonHeight, inlineResponse20010BlockEvidenceLightClientAttackEvidence.commonHeight) &&
        Objects.equals(this.byzantineValidators, inlineResponse20010BlockEvidenceLightClientAttackEvidence.byzantineValidators) &&
        Objects.equals(this.totalVotingPower, inlineResponse20010BlockEvidenceLightClientAttackEvidence.totalVotingPower) &&
        Objects.equals(this.timestamp, inlineResponse20010BlockEvidenceLightClientAttackEvidence.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(conflictingBlock, commonHeight, byzantineValidators, totalVotingPower, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse20010BlockEvidenceLightClientAttackEvidence {\n");
    sb.append("    conflictingBlock: ").append(toIndentedString(conflictingBlock)).append("\n");
    sb.append("    commonHeight: ").append(toIndentedString(commonHeight)).append("\n");
    sb.append("    byzantineValidators: ").append(toIndentedString(byzantineValidators)).append("\n");
    sb.append("    totalVotingPower: ").append(toIndentedString(totalVotingPower)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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

