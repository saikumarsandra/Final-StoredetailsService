package com.cts.training.storedetails.sftp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

    @Configuration
	@Data
	@RefreshScope
	public class SftpStoreConfiguration {
		
		@Value("${spring.security.username}")
		private String username;
		
		@Value("${spring.security.password}")
		private String password;
		
		@Value("$spring.security.role}")
		private String[] roles;
		
		
		@Bean
		public Docket swaggerConfiguration() {
			return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.paths(PathSelectors.any())
					.apis(RequestHandlerSelectors.basePackage("com.cts.training.storedetails.sftp"))
					.build();
				
	}
		
		
		@Bean
	   // @LoadBalanced
		public RestTemplate getRestTemplate() {
			return new RestTemplate();
		}
		
	}
		
