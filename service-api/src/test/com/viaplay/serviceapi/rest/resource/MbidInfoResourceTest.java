package com.viaplay.serviceapi.rest.resource;

import com.viaplay.serviceapi.service.MbidInfoService;
import com.viaplay.serviceapi.util.throttling.RateLimiterService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-18
 */
public class MbidInfoResourceTest {

    @InjectMocks
    private MbidInfoResource mbidInfoResource;

    @Mock
    private MbidInfoService mbidInfoService;

    @Mock
    private RateLimiterService rateLimiterService;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mbidInfoResource = new MbidInfoResource(mbidInfoService,rateLimiterService);
    }

    @Test
    public void resourceTest(){
        String mbid = "bb8d-1xd2-b941-9f4928627dc8-d87e52c5";
        mbidInfoResource.getMbidInfo(mbid);
        verify(mbidInfoResource,times(1)).getMbidInfo(mbid);
    }
}