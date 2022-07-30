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
import eu.frenchxcore.model.cosmossdk.openapi.ModuleIsTheTypeForVersionInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * VersionInfo is the type for the GetNodeInfoResponse message.
 */
@ApiModel(description = "VersionInfo is the type for the GetNodeInfoResponse message.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosBaseTendermintV1beta1VersionInfo {
  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_APP_NAME = "app_name";
  @SerializedName(SERIALIZED_NAME_APP_NAME)
  private String appName;

  public static final String SERIALIZED_NAME_VERSION = "version";
  @SerializedName(SERIALIZED_NAME_VERSION)
  private String version;

  public static final String SERIALIZED_NAME_GIT_COMMIT = "git_commit";
  @SerializedName(SERIALIZED_NAME_GIT_COMMIT)
  private String gitCommit;

  public static final String SERIALIZED_NAME_BUILD_TAGS = "build_tags";
  @SerializedName(SERIALIZED_NAME_BUILD_TAGS)
  private String buildTags;

  public static final String SERIALIZED_NAME_GO_VERSION = "go_version";
  @SerializedName(SERIALIZED_NAME_GO_VERSION)
  private String goVersion;

  public static final String SERIALIZED_NAME_BUILD_DEPS = "build_deps";
  @SerializedName(SERIALIZED_NAME_BUILD_DEPS)
  private List<ModuleIsTheTypeForVersionInfo> buildDeps = null;

  public static final String SERIALIZED_NAME_COSMOS_SDK_VERSION = "cosmos_sdk_version";
  @SerializedName(SERIALIZED_NAME_COSMOS_SDK_VERSION)
  private String cosmosSdkVersion;


  public CosmosBaseTendermintV1beta1VersionInfo name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public CosmosBaseTendermintV1beta1VersionInfo appName(String appName) {
    
    this.appName = appName;
    return this;
  }

   /**
   * Get appName
   * @return appName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getAppName() {
    return appName;
  }


  public void setAppName(String appName) {
    this.appName = appName;
  }


  public CosmosBaseTendermintV1beta1VersionInfo version(String version) {
    
    this.version = version;
    return this;
  }

   /**
   * Get version
   * @return version
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getVersion() {
    return version;
  }


  public void setVersion(String version) {
    this.version = version;
  }


  public CosmosBaseTendermintV1beta1VersionInfo gitCommit(String gitCommit) {
    
    this.gitCommit = gitCommit;
    return this;
  }

   /**
   * Get gitCommit
   * @return gitCommit
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getGitCommit() {
    return gitCommit;
  }


  public void setGitCommit(String gitCommit) {
    this.gitCommit = gitCommit;
  }


  public CosmosBaseTendermintV1beta1VersionInfo buildTags(String buildTags) {
    
    this.buildTags = buildTags;
    return this;
  }

   /**
   * Get buildTags
   * @return buildTags
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getBuildTags() {
    return buildTags;
  }


  public void setBuildTags(String buildTags) {
    this.buildTags = buildTags;
  }


  public CosmosBaseTendermintV1beta1VersionInfo goVersion(String goVersion) {
    
    this.goVersion = goVersion;
    return this;
  }

   /**
   * Get goVersion
   * @return goVersion
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getGoVersion() {
    return goVersion;
  }


  public void setGoVersion(String goVersion) {
    this.goVersion = goVersion;
  }


  public CosmosBaseTendermintV1beta1VersionInfo buildDeps(List<ModuleIsTheTypeForVersionInfo> buildDeps) {
    
    this.buildDeps = buildDeps;
    return this;
  }

  public CosmosBaseTendermintV1beta1VersionInfo addBuildDepsItem(ModuleIsTheTypeForVersionInfo buildDepsItem) {
    if (this.buildDeps == null) {
      this.buildDeps = new ArrayList<ModuleIsTheTypeForVersionInfo>();
    }
    this.buildDeps.add(buildDepsItem);
    return this;
  }

   /**
   * Get buildDeps
   * @return buildDeps
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<ModuleIsTheTypeForVersionInfo> getBuildDeps() {
    return buildDeps;
  }


  public void setBuildDeps(List<ModuleIsTheTypeForVersionInfo> buildDeps) {
    this.buildDeps = buildDeps;
  }


  public CosmosBaseTendermintV1beta1VersionInfo cosmosSdkVersion(String cosmosSdkVersion) {
    
    this.cosmosSdkVersion = cosmosSdkVersion;
    return this;
  }

   /**
   * Get cosmosSdkVersion
   * @return cosmosSdkVersion
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getCosmosSdkVersion() {
    return cosmosSdkVersion;
  }


  public void setCosmosSdkVersion(String cosmosSdkVersion) {
    this.cosmosSdkVersion = cosmosSdkVersion;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosBaseTendermintV1beta1VersionInfo cosmosBaseTendermintV1beta1VersionInfo = (CosmosBaseTendermintV1beta1VersionInfo) o;
    return Objects.equals(this.name, cosmosBaseTendermintV1beta1VersionInfo.name) &&
        Objects.equals(this.appName, cosmosBaseTendermintV1beta1VersionInfo.appName) &&
        Objects.equals(this.version, cosmosBaseTendermintV1beta1VersionInfo.version) &&
        Objects.equals(this.gitCommit, cosmosBaseTendermintV1beta1VersionInfo.gitCommit) &&
        Objects.equals(this.buildTags, cosmosBaseTendermintV1beta1VersionInfo.buildTags) &&
        Objects.equals(this.goVersion, cosmosBaseTendermintV1beta1VersionInfo.goVersion) &&
        Objects.equals(this.buildDeps, cosmosBaseTendermintV1beta1VersionInfo.buildDeps) &&
        Objects.equals(this.cosmosSdkVersion, cosmosBaseTendermintV1beta1VersionInfo.cosmosSdkVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, appName, version, gitCommit, buildTags, goVersion, buildDeps, cosmosSdkVersion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosBaseTendermintV1beta1VersionInfo {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    appName: ").append(toIndentedString(appName)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    gitCommit: ").append(toIndentedString(gitCommit)).append("\n");
    sb.append("    buildTags: ").append(toIndentedString(buildTags)).append("\n");
    sb.append("    goVersion: ").append(toIndentedString(goVersion)).append("\n");
    sb.append("    buildDeps: ").append(toIndentedString(buildDeps)).append("\n");
    sb.append("    cosmosSdkVersion: ").append(toIndentedString(cosmosSdkVersion)).append("\n");
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

