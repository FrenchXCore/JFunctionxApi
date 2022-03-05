package eu.frenchxcore.messages.cosmossdk.types.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Proposal defines a group proposal. Any member of a group can submit a
 * proposal for a group account to decide upon. A proposal consists of a set of
 * sdk.Msgs that will be executed if the proposal passes as well as some
 * optional metadata associated with the proposal.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Proposal {

    /**
     * proposalId is the unique id of the proposal.
     */
    @JsonProperty("proposal_id")
    public BigInteger proposalId;

    /**
     * address is the group account address.
     */
    @JsonProperty("address")
    public String address;

    /**
     * metadata is any arbitrary metadata attached to the group account.
     */
    @JsonProperty("metadata")
    public String metadata;

    /**
     * proposers are the account addresses of the proposers.
     */
    @JsonProperty("proposers")
    public List<String> proposers;

    /**
     * submittedAt is a timestamp specifying when a proposal was submitted.
     */
    @JsonProperty("submitted_at")
    public Date submittedAt;

    /**
     * groupVersion tracks the version of the group that this proposal
     * corresponds to. When group membership is changed, existing proposals from
     * previous group versions will become invalid.
     */
    @JsonProperty("group_version")
    public BigInteger groupVersion;

    /**
     * groupAccountVersion tracks the version of the group account that this
     * proposal corresponds to. When a decision policy is changed, existing
     * proposals from previous policy versions will become invalid.
     */
    @JsonProperty("group_account_version")
    public BigInteger groupAccountVersion;

    /**
     * status represents the high level position in the life cycle of the proposal. Initial value is Submitted.
     */
    @JsonProperty("status")
    public ProposalStatus status;
    
    /**
     * result is the final result based on the votes and election rule. Initial value is unfinalized. The result is persisted so that clients can always rely on this state and not have to replicate the logic.
     */
    @JsonProperty("result")
    public ProposalResult result;
    
    /**
     * voteState contains the sums of all weighted votes for this proposal.
     */
    @JsonProperty("vote_state")
    public Tally voteState;

    /**
     * timeout is the timestamp of the block where the proposal execution times out. Header times of the votes and execution messages must be before this end time to be included in the election. After the timeout timestamp the proposal can not be executed anymore and should be considered pending delete.
     */
    @JsonProperty("timeout")
    public Date timeout;

    /**
     * executorResult is the final result based on the votes and election rule. Initial value is NotRun.
     */
    @JsonProperty("executor_result")
    public ProposalExecutorResult executorResult;

    /**
     * msgs is a list of Msgs that will be executed if the proposal passes.
     */
    @JsonProperty("msgs")
    public List<Object> msgs;

}
