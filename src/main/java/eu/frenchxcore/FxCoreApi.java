package eu.frenchxcore;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import eu.frenchxcore.cosmossdk.rest.RestService;
import eu.frenchxcore.cosmossdk.rest.RestServiceManager;
import eu.frenchxcore.tools.XLogger;

public class FxCoreApi {
    
    private final RestService service;
    private final static Map<String, FxCoreApi> instances = new HashMap<>();
    
    public static FxCoreApi getInstance(String ip, int port) {
        FxCoreApi ret = null;
        try {
            InetAddress ia = InetAddress.getByName(ip);
            String key = ia.getHostAddress()+":"+port;
            if ((ret = (instances.get(key))) == null) {
                ret = new FxCoreApi(ip, port);
                instances.put(key, ret);
            }
        } catch (UnknownHostException ex) {
            XLogger.getMainLogger().error(ex.getMessage());
        }
        return ret;
    }
    
    private FxCoreApi(String ip, int port) {
        this.service = RestServiceManager.createService(ip, port);
    }

    public RestService getService() {
        return service;
    }
    
}