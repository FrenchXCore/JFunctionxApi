package eu.frenchxcore;

import eu.frenchxcore.cosmossdk.rest.RestServiceManager;

public class Example {
    
    public static void main(String[] args) {
        FxCoreApi fxMonitor = FxCoreApi.getInstance("localhost", 1317);
        eu.frenchxcore.cosmossdk.types.staking.QueryValidatorDelegationsResponse delegatorDelegations = RestServiceManager.executeSync(fxMonitor.getService(), fxMonitor.getService().stakingValidatorDelegations("fxvaloper1a73plz6w7fc8ydlwxddanc7a239kk45jnl9xwj", null));
        eu.frenchxcore.cosmossdk.types.bank.QuerySupplyOfResponse supplyOf = RestServiceManager.executeSync(fxMonitor.getService(), fxMonitor.getService().bankSupplyOf("fx"));
        int j = 0;
    }
    
}
