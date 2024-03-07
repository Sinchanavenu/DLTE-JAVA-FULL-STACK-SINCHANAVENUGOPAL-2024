package basic.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {
    public static void main(String[] args) {
        TransactionAnalysis analysis=new TransactionAnalysis();
        ScheduledExecutorService scheduledExecutorService= Executors.newScheduledThreadPool(1);
        ScheduledFuture scheduledFuture= scheduledExecutorService.scheduleAtFixedRate(analysis,2,5, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                scheduledFuture.cancel(true);
                scheduledExecutorService.shutdown();
            }
        },30,TimeUnit.SECONDS);

    }

}
