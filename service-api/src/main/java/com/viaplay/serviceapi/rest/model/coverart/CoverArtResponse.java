package com.viaplay.serviceapi.rest.model.coverart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoverArtResponse implements Serializable {

    @JsonProperty("images")
    private List<Image> images =  new LinkedList<>();

    public CoverArtResponse(){

    }
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
