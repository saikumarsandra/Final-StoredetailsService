package com.cts.training.storedetails.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.client.RestTemplate;


import lombok.Data;

@Configuration
@Data
@RefreshScope
public class Schedulerconfig {
	
	@Value("${spring.security.username}")
	private String username;
	
	@Value("${spring.security.password}")
	private String password;
	
	@Value("$spring.security.role}")
	private String[] roles;
	

	@Bean
	public RestTemplate getRestTemplate() {
		
		return new RestTemplate();
	}
}
