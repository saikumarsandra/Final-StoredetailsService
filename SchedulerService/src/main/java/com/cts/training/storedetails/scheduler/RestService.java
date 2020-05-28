package com.cts.training.storedetails.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.cts.training.storedetails.scheduler.model.Status;
@Component
public class RestService {
	
	
	@Autowired
	Schedulerconfig properties;
	
	
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(properties.getUsername(), properties.getPassword());
		return headers;
	}
	
	public HttpEntity<List<Status>> getStatusHttpEntity(){
		return new HttpEntity<>(getHeaders());
	}
	
}
