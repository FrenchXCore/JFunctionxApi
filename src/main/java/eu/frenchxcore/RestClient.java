package eu.frenchxcore;

import eu.frenchxcore.tools.XLogger;
import eu.frenchxcore.tools.rest.RestService;
import eu.frenchxcore.tools.rest.RestServiceManager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {
    
    private final RestService service;
    private final static Map<String, RestClient> instances = new HashMap<>();
    
    public static RestClient getInstance(String ip, int port) {
        XLogger.getMainLogger().trace("Creating new RestClient instance on " + ip + ":" + port);
        RestClient ret = null;
        try {
            InetAddress ia = InetAddress.getByName(ip);
            String key = ia.getHostAddress()+":"+port;
            if ((ret = (instances.get(key))) == null) {
                ret = new RestClient(ip, port);
                instances.put(key, ret);
            }
        } catch (UnknownHostException ex) {
            XLogger.getMainLogger().error(ex.getMessage());
        }
        return ret;
    }
    
    private RestClient(String ip, int port) {
        this.service = RestServiceManager.createService(ip, port);
    }

    public RestService getService() {
        return service;
    }
    
}