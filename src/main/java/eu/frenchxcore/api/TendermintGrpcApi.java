package eu.frenchxcore.api;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;
import cosmos.base.query.v1beta1.Pagination;
import cosmos.gov.v1beta2.Gov;
import cosmos.staking.v1beta1.Staking;
import cosmos.vesting.v1beta1.Vesting;
import eu.frenchxcore.tools.LocalExecutor;
import eu.frenchxcore.tools.XLogger;
import fx.gravity.v1.Types;
import ibc.core.client.v1.Client;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import org.jetbrains.annotations.NotNull;
import tendermint.abci.ABCIApplicationGrpc;
import tendermint.types.Types.Header;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class TendermintGrpcApi {

    private final ManagedChannel tendermintChannel;

    private final ABCIApplicationGrpc.ABCIApplicationFutureStub abciStub;

    private final static Map<String, TendermintGrpcApi> instances = new HashMap<>();

    public static TendermintGrpcApi getInstance(String ip, int tendermintPort) {
        return getInstance(ip, tendermintPort, null);
    }

    public static TendermintGrpcApi getInstance(String ip, int tendermintPort, Executor executor) {
        XLogger.getMainLogger().trace("Creating new TendermintGrpcApi instance on " + ip + ":" + tendermintPort);
        TendermintGrpcApi ret = null;
        try {
            InetAddress ia = InetAddress.getByName(ip);
            String key = ia.getHostAddress() + ":" + tendermintPort + ":" + tendermintPort;
            if ((ret = (instances.get(key))) == null) {
                ret = new TendermintGrpcApi(ip, tendermintPort, executor);
                instances.put(key, ret);
            }
        } catch (UnknownHostException ex) {
            XLogger.getMainLogger().error(ex.getMessage());
        }
        return ret;
    }

    /**
     * https://docs.cosmos.network/master/core/grpc_rest.html
     * https://functionx.gitbook.io/home/developers/json-rcp-abci-query
     * @param ip
     * @param tendermintPort
     * @param _executor
     */
    private TendermintGrpcApi(String ip, int tendermintPort, Executor _executor) {
        Executor executor = (_executor == null ? LocalExecutor.getInstance().get() : _executor);
        this.tendermintChannel =
                ManagedChannelBuilder
                        .forAddress(ip, tendermintPort)
                        .executor(executor)
                        .enableRetry()
                        .maxRetryAttempts(10)
                        .keepAliveWithoutCalls(true)
                        .usePlaintext()
                        .build();

        abciStub = ABCIApplicationGrpc.newFutureStub(tendermintChannel).withExecutor(executor);
    }

    public void close() {
        if (this.tendermintChannel != null) {
            try {
                this.tendermintChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                XLogger.getMainLogger().error(ex);
            }
        }
    }

    /********************************************************
     ***** TENDERMINT Module 'abci'
     *******************************************************/

    /**
     * Echo a string to test an abci client/server implementation
     *
     * @param message A string to echo back
     */
    public ListenableFuture<tendermint.abci.Types.ResponseEcho> abciEcho(String message) {
        return abciStub.echo(tendermint.abci.Types.RequestEcho.newBuilder().setMessage(message).build());
    }

    /**
     * Signals that messages queued on the client should be flushed to the server.
     * It is called periodically by the client implementation to ensure asynchronous requests are actually sent,
     * and is called immediately to make a synchronous request, which returns when the Flush response comes back.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseFlush> abciFlush() {
        return abciStub.flush(tendermint.abci.Types.RequestFlush.newBuilder().build());
    }

    /**
     * Return information about the application state.
     * Used to sync Tendermint with the application during a handshake that happens on startup.
     * The returned app_version will be included in the Header of every block.
     * Tendermint expects last_block_app_hash and last_block_height to be updated during Commit, ensuring that Commit is never called twice for the same block height.
     * Note: Semantic version is a reference to semantic versioning (opens new window). Semantic versions in info will be displayed as X.X.x.
     *
     * @param version      The Tendermint software semantic version
     * @param blockVersion The Tendermint Block Protocol version
     * @param p2pVersion   The Tendermint P2P Protocol version
     */
    public ListenableFuture<tendermint.abci.Types.ResponseInfo> abciInfo(String version, long blockVersion, long p2pVersion) {
        return abciStub.info(tendermint.abci.Types.RequestInfo.newBuilder().setVersion(version).setBlockVersion(blockVersion).setP2PVersion(p2pVersion).build());
    }

    /**
     * Called once upon genesis.
     * If ResponseInitChain.Validators is empty, the initial validator set will be the RequestInitChain.Validators
     * If ResponseInitChain.Validators is not empty, it will be the initial validator set (regardless of what is in RequestInitChain.Validators).
     * This allows the app to decide if it wants to accept the initial validator set proposed by tendermint (i.e. in the genesis file), or if it
     * wants to use a different one (perhaps computed based on some application specific information in the genesis file).
     * Both ResponseInitChain.Validators and ResponseInitChain.Validators are ValidatorUpdate structs. So, technically, they both are updating
     * the set of validators from the empty set.
     *
     * @param time            Genesis time
     * @param chainId         ID of the blockchain.
     * @param consensusParams Initial consensus-critical parameters.
     * @param validators      Initial genesis validators, sorted by voting power.
     * @param appStateBytes   Serialized initial application state. JSON bytes.
     * @param initialHeight   Height of the initial block (typically 1).
     */
    public ListenableFuture<tendermint.abci.Types.ResponseInitChain> abciInitChain(Timestamp time, String chainId, tendermint.abci.Types.ConsensusParams consensusParams, Iterable<tendermint.abci.Types.ValidatorUpdate> validators, ByteString appStateBytes, long initialHeight) {
        return abciStub.initChain(tendermint.abci.Types.RequestInitChain.newBuilder()
                .setTime(time)
                .setChainId(chainId)
                .setConsensusParams(consensusParams)
                .addAllValidators(validators)
                .setAppStateBytes(appStateBytes)
                .setInitialHeight(initialHeight)
                .build());
    }

    /**
     * Query for data from the application at current or past height.
     * Optionally return Merkle proof.
     * Merkle proof includes self-describing type field to support many types of Merkle trees and encoding formats.
     *
     * @param path   Path field of the request URI. Can be used with or in lieu of data. Apps MUST interpret /store as a query by key on the underlying store.
     *               The key SHOULD be specified in the data field. Apps SHOULD allow queries over specific types like /accounts/... or /votes/...
     * @param data   Raw query bytes. Can be used with or in lieu of Path.
     * @param prove  Return Merkle proof with response if possible
     * @param height The block height for which you want the query (default=0 returns data for the latest committed block).
     *               Note that this is the height of the block containing the application's Merkle root hash, which represents the state as
     *               it was after committing the block at Height-1
     */
    public ListenableFuture<tendermint.abci.Types.ResponseQuery> abciQuery(String path, ByteString data, Boolean prove, Long height) {
        tendermint.abci.Types.RequestQuery.Builder rq = tendermint.abci.Types.RequestQuery.newBuilder();
        if (path != null) {
            rq.setPath(path);
        }
        if (data != null && !data.isEmpty()) {
            rq.setData(data);
        }
        rq.setHeight(height != null ? height : 0L);
        rq.setProve(prove != null ? prove : true);
        return abciStub.query(rq.build());
    }

    /**
     * Technically optional - not involved in processing blocks.
     * Guardian of the mempool: every node runs CheckTx before letting a transaction into its local mempool.
     * The transaction may come from an external user or another node
     * CheckTx validates the transaction against the current state of the application, for example, checking signatures and account balances, but does not apply any of the state changes described in the transaction. not running code in a virtual machine.
     * Transactions where ResponseCheckTx.Code != 0 will be rejected - they will not be broadcast to other nodes or included in a proposal block.
     * Tendermint attributes no other value to the response code
     *
     * @param tx   The request transaction bytes
     * @param type One of CheckTx_New or CheckTx_Recheck.
     *             CheckTx_New is the default and means that a full check of the transaction is required.
     *             CheckTx_Recheck types are used when the mempool is initiating a normal recheck of a transaction.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseCheckTx> abciCheckTx(ByteString tx, tendermint.abci.Types.CheckTxType type) {
        tendermint.abci.Types.RequestCheckTx.Builder rq = tendermint.abci.Types.RequestCheckTx.newBuilder();
        if (tx != null && !tx.isEmpty()) {
            rq.setTx(tx);
        }
        rq.setType(type);
        return abciStub.checkTx(rq.build());
    }

    /**
     * Empty request asking the application for a list of snapshots.
     * Used during state sync to discover available snapshots on peers.
     * See Snapshot data type for details.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseListSnapshots> abciListSnapshots() {
        return abciStub.listSnapshots(tendermint.abci.Types.RequestListSnapshots.newBuilder().build());
    }

    /**
     * Used during state sync to retrieve snapshot chunks from peers.
     *
     * @param height The height of the snapshot the chunk belongs to.
     * @param format The application-specific format of the snapshot the chunk belongs to.
     * @param chunk  The chunk index, starting from 0 for the initial chunk.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseLoadSnapshotChunk> abciLoadSnapshotChunk(Long height, Integer format, Integer chunk) {
        return abciStub.loadSnapshotChunk(
                tendermint.abci.Types.RequestLoadSnapshotChunk.newBuilder()
                        .setHeight(height)
                        .setFormat(format)
                        .setChunk(chunk)
                        .build());
    }

    /**
     * OfferSnapshot is called when bootstrapping a node using state sync. The application may accept or reject snapshots as appropriate.
     * Upon accepting, Tendermint will retrieve and apply snapshot chunks via ApplySnapshotChunk. The application may also choose to reject
     * a snapshot in the chunk response, in which case it should be prepared to accept further OfferSnapshot calls.
     * Only AppHash can be trusted, as it has been verified by the light client. Any other data can be spoofed by adversaries, so applications
     * should employ additional verification schemes to avoid denial-of-service attacks. The verified AppHash is automatically checked against
     * the restored application at the end of snapshot restoration.
     * For more information, see the Snapshot data type or the state sync section.
     *
     * @param snapshot The snapshot offered for restoration.
     * @param appHash  The light client-verified app hash for this height, from the blockchain.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseOfferSnapshot> abciOfferSnapshot(tendermint.abci.Types.Snapshot snapshot, ByteString appHash) {
        return abciStub.offerSnapshot(
                tendermint.abci.Types.RequestOfferSnapshot.newBuilder()
                        .setSnapshot(snapshot)
                        .setAppHash(appHash)
                        .build());
    }

    /**
     * The application can choose to refetch chunks and/or ban P2P peers as appropriate. Tendermint will not do this unless instructed by the application.
     * The application may want to verify each chunk, e.g. by attaching chunk hashes in Snapshot.Metadata and/or incrementally verifying contents against AppHash.
     * When all chunks have been accepted, Tendermint will make an ABCI Info call to verify that LastBlockAppHash and LastBlockHeight matches the expected values,
     *  and record the AppVersion in the node state. It then switches to fast sync or consensus and joins the network.
     * If Tendermint is unable to retrieve the next chunk after some time (e.g. because no suitable peers are available), it will reject the snapshot and try
     *  a different one via OfferSnapshot. The application should be prepared to reset and accept it or abort as appropriate.
     *
     * @param index The chunk index, starting from 0. Tendermint applies chunks sequentially.
     * @param chunk The binary chunk contents, as returned by LoadSnapshotChunk.
     * @param sender The P2P ID of the node who sent this chunk.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseApplySnapshotChunk> abciApplySnapshotChunk(Integer index, ByteString chunk, String sender) {
        return abciStub.applySnapshotChunk(
                tendermint.abci.Types.RequestApplySnapshotChunk.newBuilder()
                        .setIndex(index)
                        .setChunk(chunk)
                        .setSender(sender)
                        .build());
    }

    /**
     * Signals the beginning of a new block.
     * Called prior to any DeliverTx method calls.
     * The header contains the height, timestamp, and more - it exactly matches the Tendermint block header. We may seek to generalize this in the future.
     * The LastCommitInfo and ByzantineValidators can be used to determine rewards and punishments for the validators.
     *
     * @param hash The block's hash. This can be derived from the block header.
     * @param header The block header.
     * @param lastCommitInfo Info about the last commit, including the round, and the list of validators and which ones signed the last block.
     * @param byzantineValidators List of evidence of validators that acted maliciously.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseBeginBlock> abciBeginBlock(ByteString hash, Header header, tendermint.abci.Types.LastCommitInfo lastCommitInfo, Collection<tendermint.abci.Types.Evidence> byzantineValidators) {
        return abciStub.beginBlock(
                tendermint.abci.Types.RequestBeginBlock.newBuilder()
                        .setHash(hash)
                        .setHeader(header)
                        .setLastCommitInfo(lastCommitInfo)
                        .addAllByzantineValidators(byzantineValidators)
                        .build());
    }

    /**
     * The core method of the application.
     * When DeliverTx is called, the application must execute the transaction in full before returning control to Tendermint.
     * ResponseDeliverTx.Code == 0 only if the transaction is fully valid.
     *
     * @param tx The request transaction bytes.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseDeliverTx> abciDeliverTx(ByteString tx) {
        return abciStub.deliverTx(
                tendermint.abci.Types.RequestDeliverTx.newBuilder()
                        .setTx(tx)
                        .build());
    }

    /**
     * Signals the end of a block.
     * Called after all the transactions for the current block have been delivered, prior to the block's Commit message.
     * Optional validator_updates triggered by block H. These updates affect validation for blocks H+1, H+2, and H+3.
     * Heights following a validator update are affected in the following way:
     * H+1: NextValidatorsHash includes the new validator_updates value.
     * H+2: The validator set change takes effect and ValidatorsHash is updated.
     * H+3: LastCommitInfo is changed to include the altered validator set.
     * consensus_param_updates returned for block H apply to the consensus params for block H+1. For more information on the consensus parameters, see the application spec entry on consensus parameters.
     *
     * @param height Height of the block just executed.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseEndBlock> abciEndBlock(long height) {
        return abciStub.endBlock(
                tendermint.abci.Types.RequestEndBlock.newBuilder()
                        .setHeight(height)
                        .build());
    }

    /**
     * Commit signals the application to persist application state. It takes no parameters.
     * Signal the application to persist the application state.
     * Return an (optional) Merkle root hash of the application state
     * ResponseCommit.Data is included as the Header.AppHash in the next block
     * it may be empty
     * Later calls to Query can return proofs about the application state anchored in this Merkle root hash
     * Note developers can return whatever they want here (could be nothing, or a constant string, etc.), so long as it is deterministic - it must not be a function of anything that did not come from the BeginBlock/DeliverTx/EndBlock methods.
     * Use RetainHeight with caution! If all nodes in the network remove historical blocks then this data is permanently lost, and no new nodes will be able to join the network and bootstrap. Historical blocks may also be required for other purposes, e.g. auditing, replay of non-persisted heights, light client verification, and so on.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseCommit> abciCommit() {
        return abciStub.commit(tendermint.abci.Types.RequestCommit.newBuilder().build());
    }

}