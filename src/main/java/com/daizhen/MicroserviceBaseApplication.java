package com.daizhen;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.daizhen.beans.Quote;
import com.daizhen.storage.StorageProperties;
import com.daizhen.storage.StorageService;

@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.daizhen.mapper")
@EnableConfigurationProperties(StorageProperties.class)
@EnableDiscoveryClient
public class MicroserviceBaseApplication {

	private static final Logger log = LoggerFactory.getLogger(MicroserviceBaseApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBaseApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject(
					"http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
            storageService.deleteAll();
            storageService.init();
		};
	}
}
