package com.viaplay.serviceapi.service;

import com.viaplay.serviceapi.rest.model.coverart.CoverArtResponse;
import org.springframework.web.util.UriComponents;

import java.util.concurrent.CompletableFuture;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
public interface CoverArtService {
    CompletableFuture<CoverArtResponse> getCoverArt(UriComponents uri);
}
