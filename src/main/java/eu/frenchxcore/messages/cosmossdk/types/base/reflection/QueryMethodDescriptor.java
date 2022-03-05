package eu.frenchxcore.cosmossdk.types.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * QueryMethodDescriptor describes a queryable method of a query service no
 * other info is provided beside method name and tendermint queryable path
 * because it would be redundant with the grpc reflection service
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryMethodDescriptor {

    /**
     * name is the protobuf name (not fullname) of the method
     */
    @JsonProperty("name")
    public String name;

    /**
     * full_query_path is the path that can be used to query this method via tendermint abci.Query
     */
    @JsonProperty("full_query_path")
    public String fullQueryPath;

}