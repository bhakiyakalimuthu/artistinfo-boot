package com.viaplay.serviceapi.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-18
 */
public class RequestUriHeaderBuilderImplTest {

    @InjectMocks
    RequestUriHeaderBuilderImpl requestUriHeaderBuilder;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.requestUriHeaderBuilder = new RequestUriHeaderBuilderImpl();
    }


    @Test
    public void musicbrainzUriTest(){
        String musicbrainzUri = "http://musicbrainz.org/ws/2/artist/bb8d-1xd2-b941-9f4928627dc8-d87e52c5?fmt=json&inc=url-rels+release-groups";
        String expected = requestUriHeaderBuilder.buildUri("bb8d-1xd2-b941-9f4928627dc8-d87e52c5","http://musicbrainz.org/ws/2/",UriAction.MUSIC_BRAINZ).toString();
        assertEquals(musicbrainzUri,expected);
    }

    @Test
    public void discogsUrlTest(){
        String discogsUri = "https://api.discogs.com/artists/bb8d-1xd2-b941-9f4928627dc8-d87e52c5";
        String expected = requestUriHeaderBuilder.buildUri("bb8d-1xd2-b941-9f4928627dc8-d87e52c5","https://api.discogs.com/",UriAction.DISCOGS).toString();
        assertEquals(discogsUri,expected);
    }

    @Test
    public void coverArtUriTest(){
        String coverArtUri = "https://api.discogs.com/release-group/bb8d-1xd2-b941-9f4928627dc8-d87e52c5";
        String expected = requestUriHeaderBuilder.buildUri("bb8d-1xd2-b941-9f4928627dc8-d87e52c5","https://api.discogs.com/",UriAction.COVER_ART).toString();
        assertEquals(coverArtUri,expected);
    }
}