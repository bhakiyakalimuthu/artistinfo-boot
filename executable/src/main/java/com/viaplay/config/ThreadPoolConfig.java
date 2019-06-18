package com.viaplay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author : Bhakiyaraj Kalimuthu
 * @date : 2019-06-16
 */
@Component
@Configuration
@ConfigurationProperties("threadpool")
public class ThreadPoolConfig {

    @Value("${threadpool.size}")
    private int size;
    @Value("${threadpool.capacity}")
    private int capacity;
    @Value("${threadpool.prefix}")
    private String prefix;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
