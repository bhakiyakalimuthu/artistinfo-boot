package com.viaplay.serviceapi.rest.resource;

import com.viaplay.serviceapi.rest.model.MbidInfo;
import com.viaplay.serviceapi.service.MbidInfoService;
import com.viaplay.serviceapi.util.throttling.RateLimiterService;
import com.viaplay.serviceapi.util.validator.UUIDConstrain;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
@Controller
@RequestMapping(value = "/service-api/v1", produces = "application/json")
@Api(value = "Music Airtist info using /service-api/v1", description = "Get music artist info rest controller")
public class MbidInfoResource {

    private MbidInfoService mbidInfoService;
    private RateLimiterService rateLimiterService;
    @Autowired
    public MbidInfoResource(MbidInfoService mbidInfoService, RateLimiterService rateLimiterService) {
        this.mbidInfoService = mbidInfoService;
        this.rateLimiterService = rateLimiterService;
    }

    /**
     * Starting point to get the artistInfo
     * @param mbid Indicates (MusicBrainz Identifier) to get the Musicbrainz data
     * @return Response entity of Artistinformation and coverart albums and profiles
     */
    @GetMapping("/info/{mbid}")
    @Cacheable(value = "response")
    @ApiOperation(value = "Get Music artist information for a given MBID")
    public ResponseEntity<MbidInfo> getMbidInfo(@UUIDConstrain  @PathVariable(value = "mbid")
                                            String mbid){
        rateLimiterService.create();
        return new ResponseEntity<>(mbidInfoService.collectMbidInfo(mbid), HttpStatus.OK);

    }
}
