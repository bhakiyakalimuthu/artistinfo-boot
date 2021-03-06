package com.viaplay.serviceapi.rest.model.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReleaseGroup implements Serializable {
    private String id;
    private String title;

    @JsonProperty("primary-type")
    private String primaryType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }
}
