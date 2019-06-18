package com.viaplay.serviceapi.util.throttling;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-18
 */

public class RateLimiter {
    private Semaphore semaphore;
    private int maxPermits;
    private TimeUnit timePeriod;
    private ScheduledExecutorService scheduler;

    public static RateLimiter create(int permits, TimeUnit timePeriod) {
        RateLimiter limiter = new RateLimiter(permits, timePeriod);
        limiter.schedulePermitReplenishment();
        return limiter;
    }

    private RateLimiter(int permits, TimeUnit timePeriod) {
        this.semaphore = new Semaphore(permits);
        this.maxPermits = permits;
        this.timePeriod = timePeriod;
    }

    public boolean tryAcquire() {
        return semaphore.tryAcquire();
    }

    public void stop() {
        scheduler.shutdownNow();
    }

    public void schedulePermitReplenishment() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleWithFixedDelay(() -> {
            semaphore.release(maxPermits - semaphore.availablePermits());
        }, 0, 1, timePeriod);

    }
}
