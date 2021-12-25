package eu.frenchxcore.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocalExecutor {
    
    private static LocalExecutor INSTANCE;
    private final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    
    public static synchronized LocalExecutor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalExecutor();
        }
        return INSTANCE;
    }
    
    public ExecutorService get() {
        return this.service;
    }
    
}
