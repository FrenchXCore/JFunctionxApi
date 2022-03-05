package eu.frenchxcore;

import com.google.protobuf.InvalidProtocolBufferException;
import eu.frenchxcore.tools.rest.RestServiceManager;

import java.util.concurrent.ExecutionException;

public class Example {
    
    public static void main(String[] args) {
        try {
            GrpcClient client = GrpcClient.getInstance("localhost", 9090);
            cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse resp0 = client.stakingValidators().get();
            cosmos.auth.v1beta1.QueryAccountResponse resp1 = client.authAccount("fx1z67rkadwrp2nf4zwxpktpqnw969plely6rfzpt").get();
            if (resp1.getAccount().is(cosmos.auth.v1beta1.BaseAccount.class)) {
                cosmos.auth.v1beta1.BaseAccount account = resp1.getAccount().unpack(cosmos.auth.v1beta1.BaseAccount.class);
            }
            cosmos.auth.v1beta1.QueryAccountResponse resp2 = client.authAccount("fx1q2lnaudfxm9l06td642jae9kmhwsq6zpt905uj").get();
            if (resp2.getAccount().is(cosmos.auth.v1beta1.BaseAccount.class)) {
                cosmos.auth.v1beta1.BaseAccount account = resp2.getAccount().unpack(cosmos.auth.v1beta1.BaseAccount.class);
            }
            cosmos.auth.v1beta1.QueryParamsResponse resp3 = client.authParams().get();
        } catch (InterruptedException | InvalidProtocolBufferException | ExecutionException e) {
            e.printStackTrace();
        }

        RestClient fxMonitor = RestClient.getInstance("localhost", 1317);
        eu.frenchxcore.messages.cosmossdk.query.staking.QueryValidatorDelegationsResponse delegatorDelegations =
                RestServiceManager.executeSync(
                        fxMonitor.getService(), 
                        fxMonitor.getService().stakingValidatorDelegations(
                                "fxvaloper1a73plz6w7fc8ydlwxddanc7a239kk45jnl9xwj", 
                                null, 
                                null, 
                                null, 
                                null, 
                                null
                        )
                );
        eu.frenchxcore.messages.cosmossdk.query.bank.QuerySupplyOfResponse supplyOf = RestServiceManager.executeSync(fxMonitor.getService(), fxMonitor.getService().bankSupplyOf("fx"));
        int j = 0;
    }
    
}
