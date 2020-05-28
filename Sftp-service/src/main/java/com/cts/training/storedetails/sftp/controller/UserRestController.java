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

import com.cts.training.storedetails.sftp.exception.ServerDownException;
import com.cts.training.storedetails.sftp.model.UserModel;
import com.cts.training.storedetails.sftp.service.datarestservice.DataRestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;
@RestController
@CrossOrigin(origins="*")
@Slf4j
public class UserRestController {
	 @Autowired
	    DataRestService restService;

		@PostMapping("/saveUser")
		@HystrixCommand(fallbackMethod ="postFallback",ignoreExceptions = HttpClientErrorException.class)
		public ResponseEntity<UserModel> createUserData6(@RequestBody UserModel user) {
			UserModel newUser =  restService.postUserData(user).getBody();
			log.info("posted user data"+newUser);
			return new ResponseEntity<UserModel>(newUser,HttpStatus.CREATED);
		}
		
		@GetMapping("users/{userName}/{loginPassword}")
		@HystrixCommand(fallbackMethod = "getFallbackUser",ignoreExceptions = HttpClientErrorException.class)
		public ResponseEntity<UserModel> getUserByNameAndPassword(@PathVariable String userName, @PathVariable String loginPassword) {
			UserModel gotUser = restService.getUserByNameAndPassword(userName, loginPassword).getBody();
			log.info("got user details:"+"username:"+userName+"  "+"password:"+loginPassword);
			return new ResponseEntity<UserModel>(gotUser,HttpStatus.OK);
		}
		
			
		public  ResponseEntity<UserModel> postFallback(@RequestBody UserModel owner){
			throw new ServerDownException("DataService is currently inactive please try again after sometime  ");
		}

		public  ResponseEntity<UserModel> getFallbackUser(@PathVariable String userName, @PathVariable String loginPassword){
			throw new ServerDownException("DataService is currently inactive please try again after sometime ");
		}
		
	}


