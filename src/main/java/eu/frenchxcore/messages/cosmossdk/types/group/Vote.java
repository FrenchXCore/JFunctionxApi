package eu.frenchxcore.messages.cosmossdk.types.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.awt.Choice;
import java.math.BigInteger;
import java.util.Date;

/**
 * Vote represents a vote for a proposal.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vote {

    /**
     * proposalId is the unique ID of the proposal.
     */
    @JsonProperty("proposal_id")
    public BigInteger proposalId;

    /**
     * voter is the account address of the voter.
     */
    @JsonProperty("voter")
    public String voter;

    /**
     * choice is the voter's choice on the proposal.
     */
    @JsonProperty("choice")
    public Choice choice;

    /**
     * metadata is any arbitrary metadata to attached to the vote.
     */
    @JsonProperty("metadata")
    public String metadata;

    /**
     * submittedAt is a timestamp specifying when a proposal was submitted.
     */
    @JsonProperty("submitted_at")
    public Date submittedAt;

}
