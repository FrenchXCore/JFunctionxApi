package eu.frenchxcore;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import cosmos.tx.v1beta1.ServiceOuterClass;
import cosmos.tx.v1beta1.TxOuterClass.Tx;
import eu.frenchxcore.api.CosmosGrpcApi;
import eu.frenchxcore.api.CosmosRestApi;
import eu.frenchxcore.api.CosmosRestServiceManager;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20011;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse2002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Example {
    
    public static void main(String[] args) {
        // Your FX node IP here
        String ip = "127.0.0.1";

        // Your FX node tendermint RPC port here
        int port = 26657;
        try {
            // Creating the GRPC client
            CosmosGrpcApi client = CosmosGrpcApi.getInstance(ip, 9090, 26658);

            // Creating the TypeRegistry, in order to decode messages to JSON format
            JsonFormat.TypeRegistry r = JsonFormat.TypeRegistry.newBuilder()
                    .add(cosmos.bank.v1beta1.Bank.getDescriptor().getMessageTypes())
                    .add(cosmos.crisis.v1beta1.Tx.getDescriptor().getMessageTypes())
                    .add(cosmos.crypto.secp256k1.Keys.getDescriptor().getMessageTypes())
                    .add(cosmos.distribution.v1beta1.Tx.getDescriptor().getMessageTypes())
                    .add(cosmos.evidence.v1beta1.Tx.getDescriptor().getMessageTypes())
                    .add(cosmos.feegrant.v1beta1.Tx.getDescriptor().getMessageTypes())
                    .add(cosmos.params.v1beta1.Params.getDescriptor().getMessageTypes())
                    .add(cosmos.slashing.v1beta1.Tx.getDescriptor().getMessageTypes())
                    .add(cosmos.staking.v1beta1.Tx.getDescriptor().getMessageTypes())
                    .add(cosmos.tx.v1beta1.TxOuterClass.getDescriptor().getMessageTypes())
                    .build();

            cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse resp0 = client.stakingQueryValidators().get();
            cosmos.auth.v1beta1.QueryAccountResponse resp1 = client.authQueryAccount("fx1z67rkadwrp2nf4zwxpktpqnw969plely6rfzpt").get();
            if (resp1.getAccount().is(cosmos.auth.v1beta1.BaseAccount.class)) {
                cosmos.auth.v1beta1.BaseAccount account = resp1.getAccount().unpack(cosmos.auth.v1beta1.BaseAccount.class);
            }
            cosmos.auth.v1beta1.QueryAccountResponse resp2 = client.authQueryAccount("fx1q2lnaudfxm9l06td642jae9kmhwsq6zpt905uj").get();
            if (resp2.getAccount().is(cosmos.auth.v1beta1.BaseAccount.class)) {
                cosmos.auth.v1beta1.BaseAccount account = resp2.getAccount().unpack(cosmos.auth.v1beta1.BaseAccount.class);
            }
            cosmos.auth.v1beta1.QueryParamsResponse resp3 = client.authQueryParams().get();

            /** To grab Cosmos events :
             * Bank:            https://github.com/cosmos/cosmos-sdk/blob/main/x/bank/spec/04_events.md
             * Crisis:          https://github.com/cosmos/cosmos-sdk/blob/main/x/crisis/spec/03_events.md
             * Distribution:    https://github.com/cosmos/cosmos-sdk/blob/main/x/distribution/spec/06_events.md
             * Evidence:        https://github.com/cosmos/cosmos-sdk/blob/main/x/evidence/spec/04_events.md
             * Feegrant:        https://github.com/cosmos/cosmos-sdk/blob/main/x/feegrant/spec/04_events.md
             * Gov:             https://github.com/cosmos/cosmos-sdk/blob/main/x/gov/spec/04_events.md
             * Group:           https://github.com/cosmos/cosmos-sdk/blob/main/x/group/spec/04_events.md
             * Mint:            https://github.com/cosmos/cosmos-sdk/blob/main/x/mint/spec/05_events.md
             * Slashing:        https://github.com/cosmos/cosmos-sdk/blob/main/x/slashing/spec/06_events.md
             * Staking:         https://github.com/cosmos/cosmos-sdk/blob/main/x/staking/spec/07_events.md
             */

            List<String> events = new ArrayList<>();
            // Adding multiple events in the list acts like an 'AND'
            events.add("message.action='edit_validator'");
            events.add("message.sender='fxvaloper1z67rkadwrp2nf4zwxpktpqnw969plelyjj5alt'");
            events.add("message.min_self_delegation!=''");
            ServiceOuterClass.GetTxsEventResponse resp4 = client.txGetTxsEvent(events, null).get();
            JsonFormat.Printer p = JsonFormat.printer().usingTypeRegistry(r).preservingProtoFieldNames().includingDefaultValueFields();
            for (Tx tx : resp4.getTxsList()) {
                System.out.println(p.print(tx));
            }

            CosmosRestApi cosmosRestApi = CosmosRestApi.getInstance(ip, 1317);

            eu.frenchxcore.model.cosmossdk.query.staking.QueryValidatorDelegationsResponse resp5 =
                    CosmosRestServiceManager.executeSync(
                            cosmosRestApi.getService(),
                            cosmosRestApi.getService().stakingValidatorDelegations(
                                    "fxvaloper1a73plz6w7fc8ydlwxddanc7a239kk45jnl9xwj",
                                    null,
                                    null,
                                    null,
                                    null,
                                    null
                            )
                    );

            eu.frenchxcore.model.cosmossdk.query.bank.QuerySupplyOfResponse resp6 = CosmosRestServiceManager.executeSync(cosmosRestApi.getService(), cosmosRestApi.getService().bankSupplyOf("fx"));

            eu.frenchxcore.model.cosmossdk.query.base.tendermint.GetLatestBlockResponse resp7 = CosmosRestServiceManager.executeSync(
                    cosmosRestApi.getService(),
                    cosmosRestApi.getService().baseTendermintGetLatestBlock());

            eu.frenchxcore.api.ApiClient apiClient = new eu.frenchxcore.api.ApiClient().setBasePath("http://"+ip+":"+port);
            eu.frenchxcore.api.tendermint.InfoApi infoApi = new eu.frenchxcore.api.tendermint.InfoApi(apiClient);
            eu.frenchxcore.api.tendermint.AbciApi abciApi = new eu.frenchxcore.api.tendermint.AbciApi(apiClient);
            eu.frenchxcore.api.tendermint.EvidenceApi evidenceApi = new eu.frenchxcore.api.tendermint.EvidenceApi(apiClient);
            try {
                eu.frenchxcore.model.tendermint.openapi.GenesisResponse genesisResponse = infoApi.genesis();
                eu.frenchxcore.model.tendermint.openapi.ABCIInfoResponse abciInfoResponse = abciApi.abciInfo();
            } catch (eu.frenchxcore.api.ApiException e) {
                e.printStackTrace();
            }

            eu.frenchxcore.api.cosmossdk.QueryApi queryApi = new eu.frenchxcore.api.cosmossdk.QueryApi(apiClient);
            eu.frenchxcore.api.cosmossdk.ServiceApi serviceApi = new eu.frenchxcore.api.cosmossdk.ServiceApi(apiClient);
            try {
                InlineResponse2002 authParams = queryApi.authParams();
                InlineResponse20011 block1 = serviceApi.getBlockByHeight("1");
            } catch (eu.frenchxcore.api.ApiException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException | InvalidProtocolBufferException | ExecutionException e) {
            e.printStackTrace();
        }

    }
    
}
