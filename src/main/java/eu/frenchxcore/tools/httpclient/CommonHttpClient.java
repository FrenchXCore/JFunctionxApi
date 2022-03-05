package eu.frenchxcore.tools.httpclient;

import eu.frenchxcore.tools.LocalExecutor;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Default
 */
public class CommonHttpClient {
    
    private static OkHttpClient INSTANCE = null;
    private final static Dispatcher DISPATCHER = new Dispatcher(LocalExecutor.getInstance().get());
    private final static ConnectionPool CONNECTION_POOL = new ConnectionPool(16, 10, TimeUnit.SECONDS);
    
    public static OkHttpClient get() {
        if (INSTANCE == null) {
            INSTANCE = new OkHttpClient.Builder()
                .connectTimeout(60000, TimeUnit.MILLISECONDS)
                .readTimeout(60000, TimeUnit.MILLISECONDS)
                .writeTimeout(60000, TimeUnit.MILLISECONDS)
                .connectionPool(CONNECTION_POOL)
                .dispatcher(DISPATCHER)
                .retryOnConnectionFailure(true)
                .build();
        }
        return INSTANCE;
    }

    public static OkHttpClient.Builder getNewBuilder() {
        return get().newBuilder();
    }
    
}