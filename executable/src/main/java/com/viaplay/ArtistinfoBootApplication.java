package com.viaplay;

import com.viaplay.config.ThreadPoolConfig;
import com.viaplay.serviceapi.config.ConfigProperties;
import com.viaplay.serviceapi.config.ServiceApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableConfigurationProperties({ThreadPoolConfig.class})
@EnableAutoConfiguration
@EnableAsync
@EnableCaching
@EnableRetry
//@EnableEurekaServer
@Import({ServiceApiConfig.class, ConfigProperties.class})
@Configuration
public class ArtistinfoBootApplication {

	private ThreadPoolConfig threadPoolConfig;
	private static final Logger LOG = LoggerFactory.getLogger(ArtistinfoBootApplication.class);
	@Autowired
	public ArtistinfoBootApplication(ThreadPoolConfig threadPoolConfig){
		this.threadPoolConfig = threadPoolConfig;
	}

	public static void main(String[] args) {
		SpringApplication.run(ArtistinfoBootApplication.class, args);
	}


	@Bean(name = "threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(threadPoolConfig.getSize());
		executor.setMaxPoolSize(threadPoolConfig.getSize());
		executor.setQueueCapacity(threadPoolConfig.getCapacity());
		executor.setThreadNamePrefix(threadPoolConfig.getPrefix());
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.initialize();
		return executor;
	}


}
