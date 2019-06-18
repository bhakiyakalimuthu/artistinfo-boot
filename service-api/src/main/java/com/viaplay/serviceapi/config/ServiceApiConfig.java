package com.viaplay.serviceapi.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
@Configuration
@ComponentScan(basePackages = {"com.viaplay.serviceapi.rest", "com.viaplay.serviceapi.service", "com.viaplay.serviceapi.util"})
@EnableWebMvc
@EnableCaching
public class ServiceApiConfig {

    private static final int CONNECTION_TIMEOUT = 1000;
    private static final int READ_TIMEOUT = 5000;

    @Bean(name = "restTemplate")
    public RestTemplate serviceApiRestTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(CONNECTION_TIMEOUT)
                .setReadTimeout(READ_TIMEOUT)
                .messageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }
}
