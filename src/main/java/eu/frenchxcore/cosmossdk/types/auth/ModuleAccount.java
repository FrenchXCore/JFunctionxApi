package eu.frenchxcore.cosmossdk.types.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.base.PublicKey;

import java.math.BigInteger;
import java.util.List;

/**
 * ModuleAccount defines an account for modules that holds coins on a pool.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModuleAccount extends Account {

    /**
     *
     */
    @JsonProperty("base_account")
    public BaseAccount baseAccount;

    /**
     *
     */
    @JsonProperty("name")
    public String name;

    /**
     *
     */
    @JsonProperty("permissions")
    public List<String> permissions;

}
