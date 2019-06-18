package com.viaplay.serviceapi.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
@Service
public class RequestUriHeaderBuilderImpl implements RequestUriHeaderBuilder {

    @Override
    public UriComponents buildUri(String value, String baseUrl, UriAction action) {

        if(action.getAction().equals(UriAction.MUSIC_BRAINZ.getAction())){
            return UriComponentsBuilder.fromHttpUrl(baseUrl+"artist/"+value)
                    .queryParam("fmt","json")
                    .queryParam("inc","url-rels+release-groups").build();
        }else if(action.getAction().equals(UriAction.COVER_ART.getAction())){
            return UriComponentsBuilder.fromHttpUrl(baseUrl+"release-group/"+value)
                    .build();
        }else if(action.getAction().equals(UriAction.DISCOGS.getAction())){
            return UriComponentsBuilder.fromHttpUrl(baseUrl+"artists/"+value)
                    .build();
        }
        return null;
    }

    public HttpEntity<String> buildHeaderEntity(String key, String secret) {
        String authorizationHeader = String.format("Discogs key=%s, secret=%s", key, secret);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, authorizationHeader);
        return new HttpEntity<>("parameters", headers);
    }
}
