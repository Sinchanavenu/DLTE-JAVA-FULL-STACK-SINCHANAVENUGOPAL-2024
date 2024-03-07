package basic.service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Execute {
    public static void main(String[] args) {
        TransactionAnalysis analysis=new TransactionAnalysis();
        Executor executor=Executors.newCachedThreadPool();
        executor.execute(analysis);
        ThreadPoolExecutor poolExecutor=(ThreadPoolExecutor) executor;
        poolExecutor.shutdown();

    }
}
