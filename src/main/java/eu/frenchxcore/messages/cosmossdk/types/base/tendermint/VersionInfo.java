package eu.frenchxcore.cosmossdk.types.base.tendermint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VersionInfo {

    /**
     * 
     */
    @JsonProperty("name")
    public String name;
    
    /**
     * 
     */
    @JsonProperty("app_name")
    public String appName;
    
    /**
     * 
     */
    @JsonProperty("version")
    public String version;
    
    /**
     * 
     */
    @JsonProperty("git_commit")
    public String gitCommit;
    
    /**
     * 
     */
    @JsonProperty("build_tags")
    public String buildTags;
    
    /**
     * 
     */
    @JsonProperty("go_version")
    public String goVersion;
    
    /**
     * 
     */
    @JsonProperty("build_deps")
    public List<Module> buildDeps;
    
    /**
     * 
     */
    @JsonProperty("cosmos_sdk_version")
    public String cosmosSdkVersion;
    
}