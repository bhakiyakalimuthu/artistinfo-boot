package com.viaplay.serviceapi.service;

import com.viaplay.serviceapi.config.ConfigProperties;
import com.viaplay.serviceapi.rest.model.MbidInfo;
import com.viaplay.serviceapi.rest.model.coverart.CoverArtResponse;
import com.viaplay.serviceapi.rest.model.discogs.DiscogsResponse;
import com.viaplay.serviceapi.rest.model.musicbrainz.MusicBrainzResponse;
import com.viaplay.serviceapi.rest.model.musicbrainz.Relation;
import com.viaplay.serviceapi.rest.model.musicbrainz.ReleaseGroup;
import com.viaplay.serviceapi.rest.model.musicbrainz.Url;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-18
 */
public class MbidInfoServiceImplTest {

    @InjectMocks
    MbidInfoServiceImpl mbidInfoService;

    @Mock
    private ConfigProperties config;

    @Mock
    private MusicBrainzService musicBrainzService;

    @Mock
    private CoverArtService coverArtService;

    @Mock
    private DiscogsService discogsService;

    @Mock
    private RequestUriHeaderBuilder requestUriHeaderBuilder;

    private static final String MBID = "bb8d-1xd2-b941-9f4928627dc8-d87e52c5";
    private static final String DISCOGS_ID = "69866";
    private static final String ALBUM_ID = "14f8524c-0b6b-440a-b854-10eb4c4371d4";
    private static final String ALBUM_TITLE = "I love you";
    private static final String ALBUM_IMAGE = "http://coverartarchive.org/release/4794bd25-0c28-41f9-af6f-fe27580de6a8/3125936037.jpg";

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mbidInfoService = mock(MbidInfoServiceImpl.class);
    }

    @Test
    public void getMbidInfoTest() throws Exception {
        when(requestUriHeaderBuilder.buildUri(MBID,"url",UriAction.MUSIC_BRAINZ)).thenReturn(UriComponentsBuilder.fromHttpUrl("https://musicurl").build());
        when(requestUriHeaderBuilder.buildUri(DISCOGS_ID,"url",UriAction.DISCOGS)).thenReturn(UriComponentsBuilder.fromHttpUrl("https://discogurl").build());
        when(requestUriHeaderBuilder.buildUri(ALBUM_ID,"url",UriAction.COVER_ART)).thenReturn(UriComponentsBuilder.fromHttpUrl("https://coverurl").build());
        when(musicBrainzService.getArtistInfo(requestUriHeaderBuilder.buildUri(MBID,"url",UriAction.MUSIC_BRAINZ))).thenReturn(getMbidInfo());
        when(requestUriHeaderBuilder.buildHeaderEntity("key","value")).thenReturn(new HttpEntity<>("hello"));
        when(discogsService.getDiscogsProfile(requestUriHeaderBuilder.buildUri(DISCOGS_ID,"url",UriAction.DISCOGS), requestUriHeaderBuilder.buildHeaderEntity("key","value"))).thenReturn(getProfile());
        when(coverArtService.getCoverArt(requestUriHeaderBuilder.buildUri(ALBUM_ID,"url",UriAction.COVER_ART))).thenReturn(getCompletableAlbum());
        when(mbidInfoService.getArtistIdInDiscogs(getMbidInfo())).thenReturn(DISCOGS_ID);
        MbidInfo mbidInfo = mbidInfoService.collectMbidInfo(MBID);
        verify(musicBrainzService,times(0)).getArtistInfo(requestUriHeaderBuilder.buildUri(MBID,"url",UriAction.MUSIC_BRAINZ));
        verify(discogsService,times(0)).getDiscogsProfile(requestUriHeaderBuilder.buildUri(DISCOGS_ID,"url",UriAction.DISCOGS), requestUriHeaderBuilder.buildHeaderEntity("key","value"));
        verify(coverArtService,times(0)).getCoverArt(requestUriHeaderBuilder.buildUri(ALBUM_ID,"url",UriAction.COVER_ART));

    }

    private MusicBrainzResponse getMbidInfo(){
        MusicBrainzResponse mbr = new MusicBrainzResponse();
        mbr.setId("d87e52c5-bb8d-4da8-b941-9f4928627dc8");
        mbr.setName("ABBA");

        Url url1 = new Url();
        url1.setResource("https://www.discogs.com/artist/69866");
        url1.setId("939530d8-a928-460f-878b-a0b8e4bda5dd");
        Url url2 = new Url();
        url2.setResource("http://www.abbachat.com/");
        url2.setId("d94ba246-f89c-4907-a330-20d62c54ce43");
        Relation relation1 = new Relation();
        relation1.setRelationType("discogs");
        relation1.setUrl(url1);

        Relation relation2 = new Relation();
        relation2.setRelationType("fanpage");
        relation2.setUrl(url2);


        List<Relation> relations = new ArrayList<>();
        relations.add(relation1);
        relations.add(relation2);

        ReleaseGroup rg1 = new ReleaseGroup();
        rg1.setId(MBID);
        rg1.setTitle(ALBUM_TITLE);
        List<ReleaseGroup> releaseGroups = new ArrayList<>();
        releaseGroups.add(rg1);

        mbr.setReleaseGroups(releaseGroups);
        mbr.setRelations(relations);
        return mbr;
    }

    private DiscogsResponse getProfile(){
        DiscogsResponse discogsResponse = new DiscogsResponse();
        discogsResponse.setProfile("Swedish pop group, active from 1972 until 1982.");
        discogsResponse.setUrls(new ArrayList<>(
                Arrays.asList(
                        "https://twitter.com/ABBATheMuseum",
                        "https://www.instagram.com/abbathemuseum/",
                        "https://en.wikipedia.org/wiki/ABBA",
                        "http://www.abba4therecord.com/",
                        "https://abbasite.com/",
                        "https://www.facebook.com/ABBA/",
                        "https://twitter.com/ABBA",
                        "https://www.instagram.com/abbaofficial/",
                        "https://www.youtube.com/user/abba",
                        "https://www.youtube.com/user/AbbaVEVO",
                        "https://www.abbathemuseum.com/",
                        "https://www.facebook.com/AbbaTheMuseum/",
                        "http://www.abbaomnibus.net/",
                        "http://www.abbaplaza.com/",
                        "https://www.facebook.com/letstalkaboutabba/?tn-str=k*F",
                        "https://musicianbio.org/abba/",
                        "http://www.musicianguide.com/biographies/1608000008/Abba.html",
                        "https://www.astro.com/astro-databank/Entertainment:_Abba",
                        "https://www.bookogs.com/credit/167108-abba",
                        "https://www.filmo.gs/credit/176653-abba")));
        return discogsResponse;
    }

    public CompletableFuture<CoverArtResponse> getCompletableAlbum(){
        CoverArtResponse coverArtResponse = new CoverArtResponse();
        List list = new LinkedList();
        list.add("http://coverartarchive.org/release/4794bd25-0c28-41f9-af6f-fe27580de6a8/3125936037.jpg");
        coverArtResponse.setImages(list);
        return CompletableFuture.supplyAsync(() -> coverArtResponse);
    }
}