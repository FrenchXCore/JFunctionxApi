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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20050TxResponseLogs;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse200Accounts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TxResponse defines a structure containing relevant tx data and metadata. The tags are stringified and the log is JSON decoded.
 */
@ApiModel(description = "TxResponse defines a structure containing relevant tx data and metadata. The tags are stringified and the log is JSON decoded.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosBaseAbciV1beta1TxResponse {
  public static final String SERIALIZED_NAME_HEIGHT = "height";
  @SerializedName(SERIALIZED_NAME_HEIGHT)
  private String height;

  public static final String SERIALIZED_NAME_TXHASH = "txhash";
  @SerializedName(SERIALIZED_NAME_TXHASH)
  private String txhash;

  public static final String SERIALIZED_NAME_CODESPACE = "codespace";
  @SerializedName(SERIALIZED_NAME_CODESPACE)
  private String codespace;

  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private Long code;

  public static final String SERIALIZED_NAME_DATA = "data";
  @SerializedName(SERIALIZED_NAME_DATA)
  private String data;

  public static final String SERIALIZED_NAME_RAW_LOG = "raw_log";
  @SerializedName(SERIALIZED_NAME_RAW_LOG)
  private String rawLog;

  public static final String SERIALIZED_NAME_LOGS = "logs";
  @SerializedName(SERIALIZED_NAME_LOGS)
  private List<InlineResponse20050TxResponseLogs> logs = null;

  public static final String SERIALIZED_NAME_INFO = "info";
  @SerializedName(SERIALIZED_NAME_INFO)
  private String info;

  public static final String SERIALIZED_NAME_GAS_WANTED = "gas_wanted";
  @SerializedName(SERIALIZED_NAME_GAS_WANTED)
  private String gasWanted;

  public static final String SERIALIZED_NAME_GAS_USED = "gas_used";
  @SerializedName(SERIALIZED_NAME_GAS_USED)
  private String gasUsed;

  public static final String SERIALIZED_NAME_TX = "tx";
  @SerializedName(SERIALIZED_NAME_TX)
  private InlineResponse200Accounts tx;

  public static final String SERIALIZED_NAME_TIMESTAMP = "timestamp";
  @SerializedName(SERIALIZED_NAME_TIMESTAMP)
  private String timestamp;


  public CosmosBaseAbciV1beta1TxResponse height(String height) {
    
    this.height = height;
    return this;
  }

   /**
   * Get height
   * @return height
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getHeight() {
    return height;
  }


  public void setHeight(String height) {
    this.height = height;
  }


  public CosmosBaseAbciV1beta1TxResponse txhash(String txhash) {
    
    this.txhash = txhash;
    return this;
  }

   /**
   * The transaction hash.
   * @return txhash
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The transaction hash.")

  public String getTxhash() {
    return txhash;
  }


  public void setTxhash(String txhash) {
    this.txhash = txhash;
  }


  public CosmosBaseAbciV1beta1TxResponse codespace(String codespace) {
    
    this.codespace = codespace;
    return this;
  }

   /**
   * Get codespace
   * @return codespace
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getCodespace() {
    return codespace;
  }


  public void setCodespace(String codespace) {
    this.codespace = codespace;
  }


  public CosmosBaseAbciV1beta1TxResponse code(Long code) {
    
    this.code = code;
    return this;
  }

   /**
   * Response code.
   * @return code
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Response code.")

  public Long getCode() {
    return code;
  }


  public void setCode(Long code) {
    this.code = code;
  }


  public CosmosBaseAbciV1beta1TxResponse data(String data) {
    
    this.data = data;
    return this;
  }

   /**
   * Result bytes, if any.
   * @return data
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Result bytes, if any.")

  public String getData() {
    return data;
  }


  public void setData(String data) {
    this.data = data;
  }


  public CosmosBaseAbciV1beta1TxResponse rawLog(String rawLog) {
    
    this.rawLog = rawLog;
    return this;
  }

   /**
   * The output of the application&#39;s logger (raw string). May be non-deterministic.
   * @return rawLog
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The output of the application's logger (raw string). May be non-deterministic.")

  public String getRawLog() {
    return rawLog;
  }


  public void setRawLog(String rawLog) {
    this.rawLog = rawLog;
  }


  public CosmosBaseAbciV1beta1TxResponse logs(List<InlineResponse20050TxResponseLogs> logs) {
    
    this.logs = logs;
    return this;
  }

  public CosmosBaseAbciV1beta1TxResponse addLogsItem(InlineResponse20050TxResponseLogs logsItem) {
    if (this.logs == null) {
      this.logs = new ArrayList<InlineResponse20050TxResponseLogs>();
    }
    this.logs.add(logsItem);
    return this;
  }

   /**
   * The output of the application&#39;s logger (typed). May be non-deterministic.
   * @return logs
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The output of the application's logger (typed). May be non-deterministic.")

  public List<InlineResponse20050TxResponseLogs> getLogs() {
    return logs;
  }


  public void setLogs(List<InlineResponse20050TxResponseLogs> logs) {
    this.logs = logs;
  }


  public CosmosBaseAbciV1beta1TxResponse info(String info) {
    
    this.info = info;
    return this;
  }

   /**
   * Additional information. May be non-deterministic.
   * @return info
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Additional information. May be non-deterministic.")

  public String getInfo() {
    return info;
  }


  public void setInfo(String info) {
    this.info = info;
  }


  public CosmosBaseAbciV1beta1TxResponse gasWanted(String gasWanted) {
    
    this.gasWanted = gasWanted;
    return this;
  }

   /**
   * Amount of gas requested for transaction.
   * @return gasWanted
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Amount of gas requested for transaction.")

  public String getGasWanted() {
    return gasWanted;
  }


  public void setGasWanted(String gasWanted) {
    this.gasWanted = gasWanted;
  }


  public CosmosBaseAbciV1beta1TxResponse gasUsed(String gasUsed) {
    
    this.gasUsed = gasUsed;
    return this;
  }

   /**
   * Amount of gas consumed by transaction.
   * @return gasUsed
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Amount of gas consumed by transaction.")

  public String getGasUsed() {
    return gasUsed;
  }


  public void setGasUsed(String gasUsed) {
    this.gasUsed = gasUsed;
  }


  public CosmosBaseAbciV1beta1TxResponse tx(InlineResponse200Accounts tx) {
    
    this.tx = tx;
    return this;
  }

   /**
   * Get tx
   * @return tx
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse200Accounts getTx() {
    return tx;
  }


  public void setTx(InlineResponse200Accounts tx) {
    this.tx = tx;
  }


  public CosmosBaseAbciV1beta1TxResponse timestamp(String timestamp) {
    
    this.timestamp = timestamp;
    return this;
  }

   /**
   * Time of the previous block. For heights &gt; 1, it&#39;s the weighted median of the timestamps of the valid votes in the block.LastCommit. For height &#x3D;&#x3D; 1, it&#39;s genesis time.
   * @return timestamp
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Time of the previous block. For heights > 1, it's the weighted median of the timestamps of the valid votes in the block.LastCommit. For height == 1, it's genesis time.")

  public String getTimestamp() {
    return timestamp;
  }


  public void setTimestamp(String timestamp) {
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
    CosmosBaseAbciV1beta1TxResponse cosmosBaseAbciV1beta1TxResponse = (CosmosBaseAbciV1beta1TxResponse) o;
    return Objects.equals(this.height, cosmosBaseAbciV1beta1TxResponse.height) &&
        Objects.equals(this.txhash, cosmosBaseAbciV1beta1TxResponse.txhash) &&
        Objects.equals(this.codespace, cosmosBaseAbciV1beta1TxResponse.codespace) &&
        Objects.equals(this.code, cosmosBaseAbciV1beta1TxResponse.code) &&
        Objects.equals(this.data, cosmosBaseAbciV1beta1TxResponse.data) &&
        Objects.equals(this.rawLog, cosmosBaseAbciV1beta1TxResponse.rawLog) &&
        Objects.equals(this.logs, cosmosBaseAbciV1beta1TxResponse.logs) &&
        Objects.equals(this.info, cosmosBaseAbciV1beta1TxResponse.info) &&
        Objects.equals(this.gasWanted, cosmosBaseAbciV1beta1TxResponse.gasWanted) &&
        Objects.equals(this.gasUsed, cosmosBaseAbciV1beta1TxResponse.gasUsed) &&
        Objects.equals(this.tx, cosmosBaseAbciV1beta1TxResponse.tx) &&
        Objects.equals(this.timestamp, cosmosBaseAbciV1beta1TxResponse.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(height, txhash, codespace, code, data, rawLog, logs, info, gasWanted, gasUsed, tx, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosBaseAbciV1beta1TxResponse {\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    txhash: ").append(toIndentedString(txhash)).append("\n");
    sb.append("    codespace: ").append(toIndentedString(codespace)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    rawLog: ").append(toIndentedString(rawLog)).append("\n");
    sb.append("    logs: ").append(toIndentedString(logs)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
    sb.append("    gasWanted: ").append(toIndentedString(gasWanted)).append("\n");
    sb.append("    gasUsed: ").append(toIndentedString(gasUsed)).append("\n");
    sb.append("    tx: ").append(toIndentedString(tx)).append("\n");
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

