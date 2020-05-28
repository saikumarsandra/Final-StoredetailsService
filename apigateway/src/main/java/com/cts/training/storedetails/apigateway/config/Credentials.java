package com.cts.training.storedetails.apigateway.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
@RefreshScope
public class Credentials {
	

	@Value("${spring.gateway.username}")
	private String username;
	
	@Value("${spring.gateway.password}")
	private String password;
	
	@Value("$spring.security.role}")
	private String[] roles;

}
