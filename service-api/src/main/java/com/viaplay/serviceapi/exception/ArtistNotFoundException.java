package com.viaplay.serviceapi.exception;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
public class ArtistNotFoundException extends RuntimeException {

    public ArtistNotFoundException(String message) {
        super(message);
    }

    public ArtistNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
