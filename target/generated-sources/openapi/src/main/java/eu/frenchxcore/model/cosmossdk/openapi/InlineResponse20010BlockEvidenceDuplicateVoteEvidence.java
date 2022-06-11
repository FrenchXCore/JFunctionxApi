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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20010BlockEvidenceDuplicateVoteEvidenceVoteA;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import org.threeten.bp.OffsetDateTime;

/**
 * DuplicateVoteEvidence contains evidence of a validator signed two conflicting votes.
 */
@ApiModel(description = "DuplicateVoteEvidence contains evidence of a validator signed two conflicting votes.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T23:37:17.045990200+02:00[Europe/Paris]")
public class InlineResponse20010BlockEvidenceDuplicateVoteEvidence {
  public static final String SERIALIZED_NAME_VOTE_A = "vote_a";
  @SerializedName(SERIALIZED_NAME_VOTE_A)
  private InlineResponse20010BlockEvidenceDuplicateVoteEvidenceVoteA voteA;

  public static final String SERIALIZED_NAME_VOTE_B = "vote_b";
  @SerializedName(SERIALIZED_NAME_VOTE_B)
  private InlineResponse20010BlockEvidenceDuplicateVoteEvidenceVoteA voteB;

  public static final String SERIALIZED_NAME_TOTAL_VOTING_POWER = "total_voting_power";
  @SerializedName(SERIALIZED_NAME_TOTAL_VOTING_POWER)
  private String totalVotingPower;

  public static final String SERIALIZED_NAME_VALIDATOR_POWER = "validator_power";
  @SerializedName(SERIALIZED_NAME_VALIDATOR_POWER)
  private String validatorPower;

  public static final String SERIALIZED_NAME_TIMESTAMP = "timestamp";
  @SerializedName(SERIALIZED_NAME_TIMESTAMP)
  private OffsetDateTime timestamp;


  public InlineResponse20010BlockEvidenceDuplicateVoteEvidence voteA(InlineResponse20010BlockEvidenceDuplicateVoteEvidenceVoteA voteA) {
    
    this.voteA = voteA;
    return this;
  }

   /**
   * Get voteA
   * @return voteA
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20010BlockEvidenceDuplicateVoteEvidenceVoteA getVoteA() {
    return voteA;
  }


  public void setVoteA(InlineResponse20010BlockEvidenceDuplicateVoteEvidenceVoteA voteA) {
    this.voteA = voteA;
  }


  public InlineResponse20010BlockEvidenceDuplicateVoteEvidence voteB(InlineResponse20010BlockEvidenceDuplicateVoteEvidenceVoteA voteB) {
    
    this.voteB = voteB;
    return this;
  }

   /**
   * Get voteB
   * @return voteB
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20010BlockEvidenceDuplicateVoteEvidenceVoteA getVoteB() {
    return voteB;
  }


  public void setVoteB(InlineResponse20010BlockEvidenceDuplicateVoteEvidenceVoteA voteB) {
    this.voteB = voteB;
  }


  public InlineResponse20010BlockEvidenceDuplicateVoteEvidence totalVotingPower(String totalVotingPower) {
    
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


  public InlineResponse20010BlockEvidenceDuplicateVoteEvidence validatorPower(String validatorPower) {
    
    this.validatorPower = validatorPower;
    return this;
  }

   /**
   * Get validatorPower
   * @return validatorPower
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getValidatorPower() {
    return validatorPower;
  }


  public void setValidatorPower(String validatorPower) {
    this.validatorPower = validatorPower;
  }


  public InlineResponse20010BlockEvidenceDuplicateVoteEvidence timestamp(OffsetDateTime timestamp) {
    
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
    InlineResponse20010BlockEvidenceDuplicateVoteEvidence inlineResponse20010BlockEvidenceDuplicateVoteEvidence = (InlineResponse20010BlockEvidenceDuplicateVoteEvidence) o;
    return Objects.equals(this.voteA, inlineResponse20010BlockEvidenceDuplicateVoteEvidence.voteA) &&
        Objects.equals(this.voteB, inlineResponse20010BlockEvidenceDuplicateVoteEvidence.voteB) &&
        Objects.equals(this.totalVotingPower, inlineResponse20010BlockEvidenceDuplicateVoteEvidence.totalVotingPower) &&
        Objects.equals(this.validatorPower, inlineResponse20010BlockEvidenceDuplicateVoteEvidence.validatorPower) &&
        Objects.equals(this.timestamp, inlineResponse20010BlockEvidenceDuplicateVoteEvidence.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(voteA, voteB, totalVotingPower, validatorPower, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse20010BlockEvidenceDuplicateVoteEvidence {\n");
    sb.append("    voteA: ").append(toIndentedString(voteA)).append("\n");
    sb.append("    voteB: ").append(toIndentedString(voteB)).append("\n");
    sb.append("    totalVotingPower: ").append(toIndentedString(totalVotingPower)).append("\n");
    sb.append("    validatorPower: ").append(toIndentedString(validatorPower)).append("\n");
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

