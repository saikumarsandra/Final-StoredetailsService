package com.cts.training.storedetails.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.training.storedetails.entity.User;
import com.cts.training.storedetails.model.UserListModel;
import com.cts.training.storedetails.repository.UserRepository;
import com.cts.training.storedetails.service.UserService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@CrossOrigin(origins = "*")
 class UserController {
	
	@Autowired
	private UserService userService;
	 @Autowired
	private UserRepository userRepo;
	
	@GetMapping("/users")
	public ResponseEntity<UserListModel> getall(){	
	UserListModel data = new  UserListModel();
		data.setUsers(this.userService.getall());
		ResponseEntity<UserListModel> result = new ResponseEntity<UserListModel>(data, HttpStatus.CREATED);
		log.info("recieved the list of user" +result);
		return result;
	
	}
	   @GetMapping("/users/{userId}")
	    public ResponseEntity<Optional<User>> userById(@PathVariable(value = "userId") Integer userId){
	      Optional<User> user = userRepo.findById(userId);
	      log.info("recieved the list of user" +user);
	        return ResponseEntity.ok().body(user);
	    }
	   
	   @PostMapping("/saveUser")
	    public User createUser( @RequestBody User user) {
	        return userRepo.save(user);
	    }
	   
	   @GetMapping("users/{userName}/{loginPassword}")
		public ResponseEntity<User> getUserByNameAndPasswor(@PathVariable String userName, @PathVariable String loginPassword) {
			User user = userService.getUserByNameAndPassword(userName, loginPassword);
			
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		
	   
}