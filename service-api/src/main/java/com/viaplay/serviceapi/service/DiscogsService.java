package com.viaplay.serviceapi.service;

import com.viaplay.serviceapi.rest.model.discogs.DiscogsResponse;
import org.springframework.http.HttpEntity;
import org.springframework.web.util.UriComponents;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
public interface DiscogsService {
    DiscogsResponse getDiscogsProfile(UriComponents uri, HttpEntity<String> header);
}
