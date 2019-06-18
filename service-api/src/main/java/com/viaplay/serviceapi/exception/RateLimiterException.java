package com.viaplay.serviceapi.exception;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-18
 */
public class RateLimiterException extends RuntimeException{

    public RateLimiterException(String message) {
        super(message);
    }

    public RateLimiterException(String message, Throwable cause) {
        super(message, cause);
    }
}
