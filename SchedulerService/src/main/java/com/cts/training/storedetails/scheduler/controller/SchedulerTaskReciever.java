package com.cts.training.storedetails.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import org.springframework.web.client.RestTemplate;

import com.cts.training.storedetails.scheduler.RestService;
import com.cts.training.storedetails.scheduler.model.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SchedulerTaskReciever {
	

	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	RestService restService;
	
	@Value("${StatusSchedule.url}")
	 private String url;
	
	@Value("${StatusSchedule.pro}")
	 private String pro;

	

	@Scheduled(cron = "* */3 * * * *")
	public ResponseEntity<Status> getRequest() {
		return restTemplate.exchange(url, HttpMethod.GET,
				restService.getStatusHttpEntity(), Status.class);
	}
	@Scheduled(cron = "* */4 * * * *")
	public ResponseEntity<Status> getProcess() {
		return restTemplate.exchange(pro, HttpMethod.GET,
				restService.getStatusHttpEntity(), Status.class);
	}

	
}
