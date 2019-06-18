package com.viaplay.serviceapi.rest.model.musicbrainz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicBrainzResponse implements Serializable {

    private String id;
    private String name;

    @JsonProperty("relations")
    private List<Relation> relations;

    @JsonProperty("release-groups")
    private List<ReleaseGroup> releaseGroups;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public List<ReleaseGroup> getReleaseGroups() {
        return releaseGroups;
    }

    public void setReleaseGroups(List<ReleaseGroup> releaseGroups) {
        this.releaseGroups = releaseGroups;
    }
}
