package com.viaplay.serviceapi.util.throttling;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-18
 */
@Component
@ConfigurationProperties(prefix = "ratelimit")
public class RateLimiterConfig {

    @Value("${ratelimit.requestpersecond}")
    private int requestPerSecond;


    public int getRequestPerSecond() {
        return requestPerSecond;
    }

    public void setRequestPerSecond(int requestPerSecond) {
        this.requestPerSecond = requestPerSecond;
    }


}
