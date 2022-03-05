package eu.frenchxcore.messages.ibc.types.core.client.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

/**
 * Height is a monotonically increasing data type that can be compared against
 * another Height for the purposes of updating and freezing clients.
 *
 * Normally the RevisionHeight is incremented at each height while keeping
 * RevisionNumber the same. However some consensus algorithms may choose to
 * reset the height in certain conditions e.g. hard forks, state-machine
 * breaking changes In these cases, the RevisionNumber is incremented so that
 * height continues to be monitonically increasing even as the RevisionHeight
 * gets reset
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Height {

    /**
     * the revision that the client is currently on
     */
    @JsonProperty("revision_number")
    public BigInteger revisionNumber;

    /**
     * the height within the given revision
     */
    @JsonProperty("revision_height")
    public BigInteger revisionHeight;

}