package com.viaplay.serviceapi.service;

import com.viaplay.serviceapi.rest.model.MbidInfo;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
public interface MbidInfoService {
    MbidInfo collectMbidInfo(String mbid);
}
