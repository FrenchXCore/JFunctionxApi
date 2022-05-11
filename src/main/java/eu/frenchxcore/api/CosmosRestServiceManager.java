package eu.frenchxcore.api;

import eu.frenchxcore.tools.LocalExecutor;
import eu.frenchxcore.tools.XLogger;
import eu.frenchxcore.tools.httpclient.CommonHttpClient;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;

public class CosmosRestServiceManager {
    
    private static Retrofit RETROFIT = null;
    private static CosmosRestService SERVICE = null;
    private final static int MAX_RETRIES = 10;
    private final static RetryPolicy retryPolicy = new RetryPolicy().withMaxRetries(MAX_RETRIES).withBackoff(30, 120, ChronoUnit.SECONDS, 2);
    
    protected static CosmosRestService createService(String ip) {
        return CosmosRestServiceManager.createService(ip, 1317);
    }

    protected static CosmosRestService createService(String ip, int port) {
        if (SERVICE == null) {
            OkHttpClient.Builder builder = CommonHttpClient.getNewBuilder();
            Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://" + ip + ":" + port)
                            .addConverterFactory(JacksonConverterFactory.create())
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .client(builder.build()).build();
            if (RETROFIT == null) {
                RETROFIT = retrofit;
            }
            SERVICE = retrofit.create(CosmosRestService.class);
        }
        return SERVICE;
    }

    public static <T> T executeSync(CosmosRestService service, Call<T> call) {
        return execute(service, call);
    }

    /**
     * Execute a REST asynchronous call.
     * @param <T>
     * @param service
     * @param call
     * @return
     */
    public static <T> CompletableFuture<T> executeAsync(CosmosRestService service, Call<T> call) {
        return Failsafe
                .with(retryPolicy)
                .with(LocalExecutor.getInstance().get())
                .getAsync(() -> execute(service, call));
    }
    
    /**
     * Execute a synchronous REST call and block until the response is received.
     * @param <T>
     * @param service
     * @param call
     * @return
     */
    public static <T> T execute(CosmosRestService service, Call<T> call) {
        T ret = null;
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                ret = response.body();
            } else {
                XLogger.getMainLogger().warn("RpcServiceManager::execute : Erreur {} : {}", response.code(), call.request().toString() );
            }
        } catch (IOException e) {
            XLogger.getMainLogger().warn("RpcServiceManager::execute : Exception {}", e.getMessage() );
        }
        return ret;
    }

}