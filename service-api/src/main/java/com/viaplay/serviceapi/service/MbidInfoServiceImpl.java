package com.viaplay.serviceapi.service;

import com.viaplay.serviceapi.config.ConfigProperties;
import com.viaplay.serviceapi.rest.model.Album;
import com.viaplay.serviceapi.rest.model.MbidInfo;
import com.viaplay.serviceapi.rest.model.coverart.CoverArtResponse;
import com.viaplay.serviceapi.rest.model.discogs.DiscogsResponse;
import com.viaplay.serviceapi.rest.model.musicbrainz.MusicBrainzResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
@Service
public class MbidInfoServiceImpl implements MbidInfoService {

    private static final Logger LOG = LoggerFactory.getLogger(MbidInfoServiceImpl.class);

    private ConfigProperties config;

    private MusicBrainzService musicBrainzService;

    private CoverArtService coverArtService;

    private DiscogsService discogsService;

    private RequestUriHeaderBuilder requestUriHeaderBuilder;

    @Autowired
    public MbidInfoServiceImpl(ConfigProperties config, MusicBrainzService musicBrainzService, CoverArtService coverArtService, DiscogsService discogsService, RequestUriHeaderBuilder requestUriHeaderBuilder) {
        this.config = config;
        this.musicBrainzService = musicBrainzService;
        this.coverArtService = coverArtService;
        this.discogsService = discogsService;
        this.requestUriHeaderBuilder = requestUriHeaderBuilder;
    }


    @Override
    public MbidInfo collectMbidInfo(String mbid) {
        try {
            MusicBrainzResponse musicBrainzResponse = musicBrainzService.getArtistInfo(requestUriHeaderBuilder.buildUri(mbid,config.getRequestBaseUrlMusicBrainz(),UriAction.MUSIC_BRAINZ));
            String artistId = getArtistIdInDiscogs(musicBrainzResponse);
            MbidInfo mbidInfo = new MbidInfo();
            mbidInfo.setMbid(mbid);
            if (artistId != null) {
                DiscogsResponse discogsResponse = discogsService.getDiscogsProfile(
                        requestUriHeaderBuilder
                                .buildUri(artistId,config.getRequestBaseUrlDiscogs(),UriAction.DISCOGS),
                        requestUriHeaderBuilder.buildHeaderEntity
                                (config.getRequestDiscogsKey(),config.getRequestDiscogsSecret()));
                mbidInfo.setDescription(discogsResponse.getProfile());
            }
            LOG.info(String.format("[ Releasegroup size: %s]",musicBrainzResponse.getReleaseGroups().size()));

            Map<String, CompletableFuture<CoverArtResponse>> futures = new HashMap<>();
            Map<String, Album> albumsMap = new HashMap<>();
            musicBrainzResponse.getReleaseGroups().stream()
                    .filter(releaseGroup -> "Album".equals(releaseGroup.getPrimaryType()))
                    .forEach(album -> {
                        String albumId = album.getId();
                        Album newAlbum = new Album();
                        newAlbum.setId(albumId);
                        newAlbum.setTitle(album.getTitle());
                        albumsMap.put(albumId, newAlbum);
                        CompletableFuture<CoverArtResponse> futureCoverArtResponse = coverArtService.getCoverArt(
                                requestUriHeaderBuilder.buildUri(albumId,config.getRequestBaseUrlCoverArt(),UriAction.COVER_ART));
                        futures.put(albumId,futureCoverArtResponse);
                    });
            CompletableFuture.allOf(futures.values().toArray(new CompletableFuture[futures.size()])).join();
            futures.forEach((id, future) -> {
                try {
                    Album album = albumsMap.get(id);
                    future.get().getImages().stream().forEach(image -> album.addImage(image.getImage()));
                    mbidInfo.addAlbum(album);
                } catch (Exception e){
                    LOG.error("Failed to fetch coverart album");
                }

            });
            return mbidInfo;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Access modifier was changed from private to default to enhance
     * testability
     */

     String getArtistIdInDiscogs(MusicBrainzResponse artist) throws Exception {
        List<String> discogsUrls = artist.getRelations().stream().filter(
                relation -> "discogs".equals(relation.getRelationType())
        ).map(relation -> relation.getUrl().getResource()).collect(Collectors.toList());

        if (discogsUrls != null && discogsUrls.size() > 0) {
            return getArtistId(new URL(discogsUrls.get(0)).getPath());
        }

        return null;
    }

    private static String getArtistId(String uri) {
        String[] list =  uri.split("/");
        return list[list.length - 1];
    }

}
