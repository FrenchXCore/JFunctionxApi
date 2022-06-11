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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20010BlockEvidenceDuplicateVoteEvidence;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20010BlockEvidenceLightClientAttackEvidence;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * InlineResponse20010BlockEvidenceEvidence
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T08:16:25.027082+02:00[Europe/Paris]")
public class InlineResponse20010BlockEvidenceEvidence {
  public static final String SERIALIZED_NAME_DUPLICATE_VOTE_EVIDENCE = "duplicate_vote_evidence";
  @SerializedName(SERIALIZED_NAME_DUPLICATE_VOTE_EVIDENCE)
  private InlineResponse20010BlockEvidenceDuplicateVoteEvidence duplicateVoteEvidence;

  public static final String SERIALIZED_NAME_LIGHT_CLIENT_ATTACK_EVIDENCE = "light_client_attack_evidence";
  @SerializedName(SERIALIZED_NAME_LIGHT_CLIENT_ATTACK_EVIDENCE)
  private InlineResponse20010BlockEvidenceLightClientAttackEvidence lightClientAttackEvidence;


  public InlineResponse20010BlockEvidenceEvidence duplicateVoteEvidence(InlineResponse20010BlockEvidenceDuplicateVoteEvidence duplicateVoteEvidence) {
    
    this.duplicateVoteEvidence = duplicateVoteEvidence;
    return this;
  }

   /**
   * Get duplicateVoteEvidence
   * @return duplicateVoteEvidence
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20010BlockEvidenceDuplicateVoteEvidence getDuplicateVoteEvidence() {
    return duplicateVoteEvidence;
  }


  public void setDuplicateVoteEvidence(InlineResponse20010BlockEvidenceDuplicateVoteEvidence duplicateVoteEvidence) {
    this.duplicateVoteEvidence = duplicateVoteEvidence;
  }


  public InlineResponse20010BlockEvidenceEvidence lightClientAttackEvidence(InlineResponse20010BlockEvidenceLightClientAttackEvidence lightClientAttackEvidence) {
    
    this.lightClientAttackEvidence = lightClientAttackEvidence;
    return this;
  }

   /**
   * Get lightClientAttackEvidence
   * @return lightClientAttackEvidence
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20010BlockEvidenceLightClientAttackEvidence getLightClientAttackEvidence() {
    return lightClientAttackEvidence;
  }


  public void setLightClientAttackEvidence(InlineResponse20010BlockEvidenceLightClientAttackEvidence lightClientAttackEvidence) {
    this.lightClientAttackEvidence = lightClientAttackEvidence;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse20010BlockEvidenceEvidence inlineResponse20010BlockEvidenceEvidence = (InlineResponse20010BlockEvidenceEvidence) o;
    return Objects.equals(this.duplicateVoteEvidence, inlineResponse20010BlockEvidenceEvidence.duplicateVoteEvidence) &&
        Objects.equals(this.lightClientAttackEvidence, inlineResponse20010BlockEvidenceEvidence.lightClientAttackEvidence);
  }

  @Override
  public int hashCode() {
    return Objects.hash(duplicateVoteEvidence, lightClientAttackEvidence);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse20010BlockEvidenceEvidence {\n");
    sb.append("    duplicateVoteEvidence: ").append(toIndentedString(duplicateVoteEvidence)).append("\n");
    sb.append("    lightClientAttackEvidence: ").append(toIndentedString(lightClientAttackEvidence)).append("\n");
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
