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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20010BlockEvidenceEvidence;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TendermintTypesEvidenceList
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class TendermintTypesEvidenceList {
  public static final String SERIALIZED_NAME_EVIDENCE = "evidence";
  @SerializedName(SERIALIZED_NAME_EVIDENCE)
  private List<InlineResponse20010BlockEvidenceEvidence> evidence = null;


  public TendermintTypesEvidenceList evidence(List<InlineResponse20010BlockEvidenceEvidence> evidence) {
    
    this.evidence = evidence;
    return this;
  }

  public TendermintTypesEvidenceList addEvidenceItem(InlineResponse20010BlockEvidenceEvidence evidenceItem) {
    if (this.evidence == null) {
      this.evidence = new ArrayList<InlineResponse20010BlockEvidenceEvidence>();
    }
    this.evidence.add(evidenceItem);
    return this;
  }

   /**
   * Get evidence
   * @return evidence
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<InlineResponse20010BlockEvidenceEvidence> getEvidence() {
    return evidence;
  }


  public void setEvidence(List<InlineResponse20010BlockEvidenceEvidence> evidence) {
    this.evidence = evidence;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TendermintTypesEvidenceList tendermintTypesEvidenceList = (TendermintTypesEvidenceList) o;
    return Objects.equals(this.evidence, tendermintTypesEvidenceList.evidence);
  }

  @Override
  public int hashCode() {
    return Objects.hash(evidence);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TendermintTypesEvidenceList {\n");
    sb.append("    evidence: ").append(toIndentedString(evidence)).append("\n");
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

