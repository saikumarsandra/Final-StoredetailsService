package com.cts.training.storedetails.sftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableEurekaClient
@SpringBootApplication
@EnableIntegration
@EnableCircuitBreaker
@IntegrationComponentScan
@EnableSwagger2
public class SftpServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SftpServiceApplication.class, args);
	}

}
