package com.viaplay.serviceapi.util.throttling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-18
 */
@Service
public class RateLimiterService {

    private RateLimiterConfig rateLimiterConfig;


    @Autowired
    public RateLimiterService(RateLimiterConfig rateLimiterConfig) {
        this.rateLimiterConfig = rateLimiterConfig;

    }

   public void create(){
       RateLimiter.create(rateLimiterConfig.getRequestPerSecond(), TimeUnit.MILLISECONDS);
   }

}
