package com.viaplay.serviceapi.rest.model.coverart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image implements Serializable {

    @JsonProperty("front")
    private boolean front;

    @JsonProperty("image")
    private String image;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFront() {
        return front;
    }

    public void setFront(boolean front) {
        this.front = front;
    }
}
