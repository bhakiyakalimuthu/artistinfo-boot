package com.viaplay.serviceapi.exception;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
public class CoverArtNotFoundException extends RuntimeException{

    public CoverArtNotFoundException(String message) {
        super(message);
    }

    public CoverArtNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
