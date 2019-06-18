package com.viaplay.serviceapi.util.retry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-18
 */
@Component
@Configuration(value = "retry")
public class RetryConfig {

    @Value("${retry.numberofretry}")
    private int numberOfRetry;
    @Value("${retry.backofftime}")
    private int backOffTime;

    public int getNumberOfRetry() {
        return numberOfRetry;
    }

    public void setNumberOfRetry(int numberOfRetry) {
        this.numberOfRetry = numberOfRetry;
    }

    public int getBackOffTime() {
        return backOffTime;
    }

    public void setBackOffTime(int backOffTime) {
        this.backOffTime = backOffTime;
    }
}
