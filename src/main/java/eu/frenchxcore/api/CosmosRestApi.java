package eu.frenchxcore.api;

import eu.frenchxcore.tools.XLogger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class CosmosRestApi {
    
    private final CosmosRestService service;
    private final static Map<String, CosmosRestApi> instances = new HashMap<>();
    
    public static CosmosRestApi getInstance(String ip, int port) {
        XLogger.getMainLogger().trace("Creating new RestClient instance on " + ip + ":" + port);
        CosmosRestApi ret = null;
        try {
            InetAddress ia = InetAddress.getByName(ip);
            String key = ia.getHostAddress()+":"+port;
            if ((ret = (instances.get(key))) == null) {
                ret = new CosmosRestApi(ip, port);
                instances.put(key, ret);
            }
        } catch (UnknownHostException ex) {
            XLogger.getMainLogger().error(ex.getMessage());
        }
        return ret;
    }
    
    private CosmosRestApi(String ip, int port) {
        this.service = CosmosRestServiceManager.createService(ip, port);
    }

    public CosmosRestService getService() {
        return service;
    }
    
}