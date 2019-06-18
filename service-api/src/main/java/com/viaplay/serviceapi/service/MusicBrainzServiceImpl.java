package com.viaplay.serviceapi.service;

import com.viaplay.serviceapi.exception.ArtistNotFoundException;
import com.viaplay.serviceapi.rest.model.musicbrainz.MusicBrainzResponse;
import com.viaplay.serviceapi.util.retry.RetryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

import static java.util.Collections.singletonList;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
@Service
public class MusicBrainzServiceImpl implements MusicBrainzService{

    private static final Logger LOG = LoggerFactory.getLogger(MusicBrainzServiceImpl.class);
    private RestTemplate restTemplate;
    private RetryConfig retryConfig;

    @Autowired
    public MusicBrainzServiceImpl(RetryConfig retryConfig) {
        this.retryConfig = retryConfig;
        this.restTemplate = new RestTemplate(singletonList(new MappingJackson2HttpMessageConverter()));
    }

    @Override
    @Retryable(value = {
            HttpClientErrorException.class, HttpStatusCodeException.class, RestClientException.class}, maxAttempts = 4, backoff = @Backoff(200))
    @Cacheable(value = "artist")
    public MusicBrainzResponse getArtistInfo(UriComponents uri) {
        return makeRequest(uri);
    }


    private MusicBrainzResponse makeRequest(UriComponents requestURI) {

        return requestForEntityWithExceptionHandling(requestURI);
    }

    private MusicBrainzResponse requestForEntityWithExceptionHandling(UriComponents requestURI){

        try {
            LOG.debug(String.format("RequestUri: %s", requestURI));

            MusicBrainzResponse response = restTemplate.getForObject(requestURI.toUri(), MusicBrainzResponse.class);
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("Response: %s", response));
            }
            return response;
        }catch (HttpClientErrorException e) {
            LOG.error(String.format("HttpClientErrorException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
            throw new ArtistNotFoundException(String.format("HttpClientErrorException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
        }catch (HttpStatusCodeException e) {
            LOG.error(String.format("HttpStatusCodeException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
            throw new ArtistNotFoundException(String.format("HttpStatusCodeException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
        }catch (RestClientException e) {
            LOG.error(String.format("RestClientException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
            throw new ArtistNotFoundException(String.format("RestClientException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
        }
    }

}
