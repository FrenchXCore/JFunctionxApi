package eu.frenchxcore.tools.httpclient;

import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import eu.frenchxcore.tools.LocalExecutor;

/**
 *
 * @author Default
 */
public class CommonHttpClient {
    
    private static OkHttpClient INSTANCE = null;
    private final static Dispatcher DISPATCHER = new Dispatcher(LocalExecutor.getInstance().get());
    private final static ConnectionPool CONNECTION_POOL = new ConnectionPool(4, 1, TimeUnit.SECONDS);
    
    public static OkHttpClient get() {
        if (INSTANCE == null) {
            INSTANCE = new OkHttpClient.Builder()
                .connectTimeout(2000, TimeUnit.MILLISECONDS)
                .readTimeout(2000, TimeUnit.MILLISECONDS)
                .writeTimeout(2000, TimeUnit.MILLISECONDS)
                .connectionPool(CONNECTION_POOL)
                .dispatcher(DISPATCHER)
                .retryOnConnectionFailure(false)
                .build();
        }
        return INSTANCE;
    }

    public static OkHttpClient.Builder getNewBuilder() {
        return get().newBuilder();
    }
    
}