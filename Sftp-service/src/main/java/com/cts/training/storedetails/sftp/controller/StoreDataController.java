package com.cts.training.storedetails.sftp.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.Default;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.training.storedetails.sftp.model.StoreDetails;
import com.cts.training.storedetails.sftp.repository.StoreRepository;
import com.cts.training.storedetails.sftp.service.StoreDetailsService;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class StoreDataController {
	@Autowired
	public StoreDetailsService storeService;
	@Autowired
	public StoreRepository repo;
	
	

	@GetMapping("/allstores")
	public List<StoreDetails> getall() throws IOException{	
	  
	File	file = new File("tmp\\store.csv");
		  
		 CsvMapper mapper = new CsvMapper();
	
         CsvSchema schema = mapper.schemaFor(StoreDetails.class).withHeader();
      		                   
         MappingIterator<StoreDetails> storedetails = mapper.readerFor(StoreDetails.class)
      		                                              .with(schema)
      		                                              .readValues(file);
           List<StoreDetails> storelist = storedetails.readAll();
        	log.info("==========>"+storelist);
		
		log.info("#######"+file);
		log.info("*****"+storedetails);
		return storelist;
	}	
	
	@GetMapping("/getStoreById/{ownerId}")
	public List<StoreDetails> getByOwnerId(@PathVariable Integer ownerId)
	{
	
	return  this.storeService.findByownerId(ownerId);
}
	
	@PutMapping("/updateStore/{storeId}")
    public ResponseEntity<StoreDetails> updateStore(@PathVariable(value = "storeId") Integer storeId,
          @RequestBody StoreDetails storeDetails)  {
       StoreDetails data = repo.getOne(storeId);
       data.setLocation(storeDetails.getLocation());
       data.setOwnerName(storeDetails.getOwnerName());
       data.setPhoneNumber(storeDetails.getPhoneNumber());
       data.setStoreName(storeDetails.getStoreName());
       data.setStoreType(storeDetails.getStoreType());
        final StoreDetails updatedStoreData = repo.save(data);
        return ResponseEntity.ok(updatedStoreData);
    }	
	
	 @DeleteMapping("/delete/{storeId}")
	    public Map<String, Boolean> deleteStore(@PathVariable(value = "storeId") Integer storeId){
	         
	        StoreDetails  data = repo.getOne(storeId);
	             repo.delete(data);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("processed", Boolean.TRUE);
	        return response;
	    }
	
}


