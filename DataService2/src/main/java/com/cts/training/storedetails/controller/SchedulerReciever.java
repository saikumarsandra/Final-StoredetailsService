package com.cts.training.storedetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cts.training.storedetails.service.StatusService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class SchedulerReciever {
	
	@Autowired
	public StatusService service;
	


	@GetMapping("/store/requeststatus")
	public String editStatus()
	{
		 this.service.StatusReciever();
			return "Processed";
	}
 @GetMapping("/store/processedstatus")
    public String editProcessed()
  {
		 this.service.ProcessedStatus();
			return "requested";
	}
	
}
