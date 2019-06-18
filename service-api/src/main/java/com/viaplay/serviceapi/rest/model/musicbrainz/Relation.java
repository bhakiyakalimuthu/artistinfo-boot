package com.viaplay.serviceapi.rest.model.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Relation implements Serializable {

    @JsonProperty("url")
    private Url url;

    @JsonProperty("type")
    private String relationType;


    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

}
