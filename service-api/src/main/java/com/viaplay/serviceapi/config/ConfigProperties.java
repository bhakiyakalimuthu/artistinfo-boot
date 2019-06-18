package com.viaplay.serviceapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
@Component
@ConfigurationProperties("request")
public class ConfigProperties {


    @Value("${request.baseurl.musicbrainz}")
    private String requestBaseUrlMusicBrainz;

    @Value("${request.baseurl.coverartarchive}")
    private String requestBaseUrlCoverArt;

    @Value("${request.baseurl.discogs}")
    private String requestBaseUrlDiscogs;

    @Value("${request.discogs.key}")
    private String requestDiscogsKey;

    @Value("${request.discogs.secret}")
    private String requestDiscogsSecret;

    public String getRequestBaseUrlMusicBrainz() {
        return requestBaseUrlMusicBrainz;
    }

    public void setRequestBaseUrlMusicBrainz(String requestBaseUrlMusicBrainz) {
        this.requestBaseUrlMusicBrainz = requestBaseUrlMusicBrainz;
    }

    public String getRequestBaseUrlCoverArt() {
        return requestBaseUrlCoverArt;
    }

    public void setRequestBaseUrlCoverArt(String requestBaseUrlCoverArt) {
        this.requestBaseUrlCoverArt = requestBaseUrlCoverArt;
    }

    public String getRequestBaseUrlDiscogs() {
        return requestBaseUrlDiscogs;
    }

    public void setRequestBaseUrlDiscogs(String requestBaseUrlDiscogs) {
        this.requestBaseUrlDiscogs = requestBaseUrlDiscogs;
    }

    public String getRequestDiscogsKey() {
        return requestDiscogsKey;
    }

    public void setRequestDiscogsKey(String requestDiscogsKey) {
        this.requestDiscogsKey = requestDiscogsKey;
    }

    public String getRequestDiscogsSecret() {
        return requestDiscogsSecret;
    }

    public void setRequestDiscogsSecret(String requestDiscogsSecret) {
        this.requestDiscogsSecret = requestDiscogsSecret;
    }
}
