package eu.frenchxcore.ibc.types.core.client.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.upgrade.v1beta1.Plan;
import eu.frenchxcore.tools.TypedAny;

/**
 * UpgradeProposal is a gov Content type for initiating an IBC breaking upgrade.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpgradeProposal {

    /**
     *
     */
    @JsonProperty("title")
    public String title;

    /**
     *
     */
    @JsonProperty("description")
    public String description;

    /**
     *
     */
    @JsonProperty("plan")
    public Plan plan;

    /**
     * An UpgradedClientState must be provided to perform an IBC breaking
     * upgrade. This will make the chain commit to the correct upgraded (self)
     * client state before the upgrade occurs, so that connecting chains can
     * verify that the new upgraded client is valid by verifying a proof on the
     * previous version of the chain. This will allow IBC connections to persist
     * smoothly across planned chain upgrades
     */
    @JsonProperty("upgraded_client_state")
    public TypedAny upgradedClientState;

}
