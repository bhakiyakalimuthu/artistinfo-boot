package com.viaplay.serviceapi.rest.model.discogs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscogsResponse implements Serializable {

    @JsonProperty("profile")
    private String profile;

    @JsonProperty("urls")
    private List<String> urls;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
