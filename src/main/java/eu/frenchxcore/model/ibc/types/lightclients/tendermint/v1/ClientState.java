package eu.frenchxcore.model.ibc.types.lightclients.tendermint.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.ibc.types.core.client.v1.Height;
import eu.frenchxcore.model.ibc.types.thirdparty.proofs.ProofSpec;

import java.util.List;

/**
 * ClientState from Tendermint tracks the current validator set, latest height,
 * and a possible frozen height.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientState {

    /**
     *
     */
    @JsonProperty("chain_id")
    public String chainId;

    /**
     *
     */
    @JsonProperty("trust_level")
    public Fraction trustLevel;

    /**
     * duration of the period since the LastestTimestamp during which the
     * submitted headers are valid for upgrade
     */
    @JsonProperty("trusting_period")
    public String trustingPeriod;

    /**
     * duration of the staking unbonding period
     */
    @JsonProperty("unbonding_period")
    public String unbondingPeriod;

    /**
     * defines how much new (untrusted) header's Time can drift into the future.
     */
    @JsonProperty("max_clock_drift")
    public String maxClockDrift;

    /**
     * Block height when the client was frozen due to a misbehaviour
     */
    @JsonProperty("frozen_height")
    public Height frozenHeight;

    /**
     * Latest height the client was updated to
     */
    @JsonProperty("latest_height")
    public Height latestHeight;

    /**
     * Proof specifications used in verifying counterparty state
     */
    @JsonProperty("proof_specs")
    public List<ProofSpec> proofSpecs;

    /**
     * Path at which next upgraded client will be committed. Each element
     * corresponds to the key for a single CommitmentProof in the chained proof.
     * NOTE: ClientState must stored under
     * `{upgradePath}/{upgradeHeight}/clientState` ConsensusState must be stored
     * under `{upgradepath}/{upgradeHeight}/consensusState` For SDK chains using
     * the default upgrade module, upgrade_path should be []string{"upgrade",
     * "upgradedIBCState"}`
     */
    @JsonProperty("upgrade_path")
    public List<String> upfradePath;

    /**
     * This flag, when set to true, will allow governance to recover a client
     * which has expired
     */
    @JsonProperty("allow_update_after_expiry")
    public Boolean allowUpdateAfterExpiry;

    /**
     * This flag, when set to true, will allow governance to unfreeze a client
     * whose chain has experienced a misbehaviour event
     */
    @JsonProperty("allow_update_after_misbehaviour")
    public Boolean allowUpdateAfterMisbehaviour;

}
