package com.viaplay.serviceapi.service;

import com.viaplay.serviceapi.exception.CoverArtNotFoundException;
import com.viaplay.serviceapi.rest.model.coverart.CoverArtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;



/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
@Service
public class CoverArtServiceImpl implements CoverArtService{

    private static final Logger LOG = LoggerFactory.getLogger(CoverArtServiceImpl.class);
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public CoverArtServiceImpl() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.ALL));
        this.restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
//        this.restTemplate = new RestTemplate(singletonList(new GsonHttpMessageConverter()));

    }

    @Override
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<CoverArtResponse> getCoverArt(UriComponents uri) {
        return makeRequest(uri);
    }


    private CompletableFuture<CoverArtResponse> makeRequest(UriComponents requestURI) {

        return requestForEntityWithExceptionHandling(requestURI);
    }


    private CompletableFuture<CoverArtResponse> requestForEntityWithExceptionHandling(UriComponents requestURI){

        CoverArtResponse coverArtResponse = null;
        try {
            LOG.debug(String.format("RequestUri: %s", requestURI));

            CoverArtResponse response = restTemplate.getForObject(requestURI.toUri(), CoverArtResponse.class);
            if (LOG.isDebugEnabled()) {
                    LOG.debug(String.format("Response: %s", response));
            }
            return CompletableFuture.completedFuture(response);
        }catch (HttpClientErrorException e) {
            LOG.error(String.format("HttpClientErrorException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
//            throw new CoverArtNotFoundException(String.format("HttpClientErrorException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
             coverArtResponse = new CoverArtResponse();
            return CompletableFuture.completedFuture(coverArtResponse);
        }catch (HttpStatusCodeException e) {
            LOG.error(String.format("HttpStatusCodeException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
            throw new CoverArtNotFoundException(String.format("HttpStatusCodeException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
        }catch (RestClientException e) {
            LOG.error(String.format("RestClientException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
            throw new CoverArtNotFoundException(String.format("RestClientException caught when trying to make rest call [requestUri=%s],[exception=%s]",requestURI,e.toString()));
        }
    }
}
