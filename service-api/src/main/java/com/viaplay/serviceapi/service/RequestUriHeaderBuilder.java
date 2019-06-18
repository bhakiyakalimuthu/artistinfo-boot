package com.viaplay.serviceapi.service;

import org.springframework.http.HttpEntity;
import org.springframework.web.util.UriComponents;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
public interface RequestUriHeaderBuilder {
    UriComponents buildUri(String value, String baseUrl, UriAction action);
    HttpEntity<String> buildHeaderEntity(String key, String secret);

}
