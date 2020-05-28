package com.cts.training.storedetails.sftp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cts.training.storedetails.sftp.RestClient.CommonRestClient;
import com.cts.training.storedetails.sftp.exception.ServerDownException;
import com.cts.training.storedetails.sftp.model.RetailerModel;
import com.cts.training.storedetails.sftp.service.datarestservice.DataRestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;
@RestController
@CrossOrigin(origins="*")
@Slf4j
public class RetailerRestController {

	 @Autowired
    DataRestService restService;

	@PostMapping("/saveretailer")
	@HystrixCommand(fallbackMethod ="postFallback",ignoreExceptions = HttpClientErrorException.class)
	public ResponseEntity<RetailerModel> createOwner(@RequestBody RetailerModel owner) {
		RetailerModel newOwner =  restService.postOwnerData(owner).getBody();
		log.info("posted owner data"+ newOwner);
		return new ResponseEntity<RetailerModel>(newOwner,HttpStatus.CREATED);
	}
	
	@GetMapping("owner/{ownerName}/{loginPassword}")
	@HystrixCommand(fallbackMethod = "getFallbackOwner",ignoreExceptions = HttpClientErrorException.class)
	public ResponseEntity<RetailerModel> getOwnerByNameAndPassword(@PathVariable String ownerName, @PathVariable String loginPassword) {
		RetailerModel gotOwner = restService.getOwnerByNameAndPassword(ownerName, loginPassword).getBody();
		log.info("got owner details:"+"ownername:"+ownerName+"  "+"password:"+loginPassword);
		return new ResponseEntity<RetailerModel>(gotOwner,HttpStatus.OK);
	}
	
		
	public  ResponseEntity<RetailerModel> postFallback(@RequestBody RetailerModel owner){
		throw new ServerDownException("DataService is currently inactive please try again after sometime ");
	}

	public  ResponseEntity<RetailerModel> getFallbackOwner(@PathVariable String ownerName, @PathVariable String loginPassword){
		throw new ServerDownException("DataService is currently inactive please try again after sometime ");
	}
	
}
