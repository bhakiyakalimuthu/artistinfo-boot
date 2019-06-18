package com.viaplay.serviceapi.service;

import com.viaplay.serviceapi.rest.model.musicbrainz.MusicBrainzResponse;
import org.springframework.web.util.UriComponents;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
public interface MusicBrainzService {
    MusicBrainzResponse getArtistInfo(UriComponents uri);
}
