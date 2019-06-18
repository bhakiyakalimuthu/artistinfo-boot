package com.viaplay.serviceapi.rest.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
public class Album implements Serializable {

    private String id;

    private String title;

    private List<String> images = new LinkedList<>();

    public Album() {
    }


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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void addImage(String image) {
        this.images.add(image);
    }
}
