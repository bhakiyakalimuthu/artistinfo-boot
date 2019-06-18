package com.viaplay.serviceapi.service;

import com.viaplay.serviceapi.exception.CoverArtNotFoundException;
import com.viaplay.serviceapi.rest.model.discogs.DiscogsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
public class DiscogsServiceImpl implements DiscogsService {

    private static final Logger LOG = LoggerFactory.getLogger(DiscogsServiceImpl.class);
    private RestTemplate restTemplate;

    @Autowired
    public DiscogsServiceImpl() {
        this.restTemplate = new RestTemplate(singletonList(new MappingJackson2HttpMessageConverter()));
    }

    @Override
    @Cacheable(value = "profile")
    public DiscogsResponse getDiscogsProfile(UriComponents uri, HttpEntity<String> header) {
        return makeRequest(uri, header);
    }

    private DiscogsResponse makeRequest(UriComponents requestURI, HttpEntity<String> header) {

        return requestForEntityWithExceptionHandling(requestURI,header);
    }

    private DiscogsResponse requestForEntityWithExceptionHandling(UriComponents requestURI, HttpEntity<String> header){

        try {
            LOG.debug(String.format("RequestUri: %s", requestURI));
            DiscogsResponse response = restTemplate.exchange(requestURI.toUri(), HttpMethod.GET, header, DiscogsResponse.class).getBody();
            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("Response: %s", response));
            }
            return response;
        }catch (HttpClientErrorException e) {
            LOG.error(String.format("HttpClientErrorException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
            throw new CoverArtNotFoundException(String.format("HttpClientErrorException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
        }catch (HttpStatusCodeException e) {
            LOG.error(String.format("HttpStatusCodeException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
            throw new CoverArtNotFoundException(String.format("HttpStatusCodeException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
        }catch (RestClientException e) {
            LOG.error(String.format("RestClientException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
            throw new CoverArtNotFoundException(String.format("RestClientException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
        }
    }

}
