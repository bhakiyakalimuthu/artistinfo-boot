package com.viaplay.serviceapi.rest.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
public class MbidInfo implements Serializable {

    private String mbid;

    private String description;

    private List<Album> albums = new LinkedList<>();


    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
    }
}
