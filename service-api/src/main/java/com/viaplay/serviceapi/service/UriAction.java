package com.viaplay.serviceapi.service;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
public enum UriAction {

    MUSIC_BRAINZ("musicBrainz"),
    COVER_ART("coverArt"),
    DISCOGS("discogs");

    private final String action;

    UriAction(String action){
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
