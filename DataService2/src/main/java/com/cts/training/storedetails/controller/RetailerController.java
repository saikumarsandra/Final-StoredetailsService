package com.cts.training.storedetails.controller;


import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.training.storedetails.entity.Retailer;
import com.cts.training.storedetails.entity.User;
import com.cts.training.storedetails.model.RetailerListModel;
import com.cts.training.storedetails.repository.RetailerRepository;
import com.cts.training.storedetails.service.RetailerService;


import lombok.extern.slf4j.Slf4j;



@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class RetailerController {

@Autowired
private RetailerService retailerservice;

@Autowired
private RetailerRepository retailerRepository;

@GetMapping("/retailer")
public ResponseEntity<RetailerListModel> getall(){

	RetailerListModel data = new RetailerListModel();
	data.setRetailer(this.retailerservice.getall());
	ResponseEntity<RetailerListModel> result = new ResponseEntity<RetailerListModel>(data, HttpStatus.CREATED);
	log.info("recieved the list of store" +result);
	return result;
	
}


@GetMapping("/retailer/{ownerId}")
public ResponseEntity<Optional<Retailer>> findownerById(@PathVariable(value = "ownerId") Integer ownerId){
  Optional<Retailer> retailer = retailerRepository.findById(ownerId);
  log.info("recieved the list of store" +retailer);
    return ResponseEntity.ok().body(retailer);
}

@PostMapping("/saveretailer")
public Retailer createRetailer( @RequestBody Retailer retailer) {
	log.info("recieved the list of store" +retailer);
    return retailerRepository.save(retailer);
}


@GetMapping("owner/{ownerName}/{loginPassword}")
public ResponseEntity<Retailer> geOwnerByNameAndPassword(@PathVariable String ownerName, @PathVariable String loginPassword) {
Retailer owner = retailerservice.getOwnerByNameAndPassword(ownerName, loginPassword);
	
	return new ResponseEntity<Retailer>(owner,HttpStatus.OK);
}
	  
}




	
	


