package com.cts.training.storedetails.sftp.service;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.training.storedetails.sftp.model.StoreDetails;
import com.cts.training.storedetails.sftp.repository.StoreRepository;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StoreDetailsService  {
	@Autowired
	public StoreRepository repo;
	 
	// get all data from .csv
   public List<StoreDetails> getall(File file) throws IOException {

         CsvMapper mapper = new CsvMapper();
         CsvSchema schema = mapper.schemaFor(StoreDetails.class).withHeader();
      		                 
         MappingIterator<StoreDetails> storedetails = mapper.readerFor(StoreDetails.class)
      		                                              .with(schema)
      		                                              .readValues(file);
           List<StoreDetails> storelist = storedetails.readAll();
         //  this.repo.saveAll(storelist);
		 log.info(" got list in service"+ storelist);
		 log.info("=======>"+file);
			return  storelist;
		}
   
   // save the csv data
   public void saveStoreData(File file) throws IOException{
	   
		CsvMapper mapper = new CsvMapper();
		
       CsvSchema schema = mapper.schemaFor(StoreDetails.class).withHeader();
    		                   
       MappingIterator<StoreDetails> storedetails = mapper.readerFor(StoreDetails.class)
    		                                              .with(schema)
    		                                              .readValues(file);
         List<StoreDetails> storelist = storedetails.readAll();
       this.repo.saveAll(storelist);
		log.info("==========>"+storelist);
		
		log.info("==========>"+file);
		log.info("==========>"+storedetails);
	
	}
 // get by ownerId  
   public List<StoreDetails> findByownerId(Integer ownerId){
  	 
   	
  	 return repo.findByownerId(ownerId);
		}
   
   public ResponseEntity<StoreDetails> updateStore(@PathVariable(value = "storeId") Integer storeId,
                                                     @RequestBody StoreDetails
                                                     storeDetails)  {
        StoreDetails data = repo.getOne(storeId);
        data.setLocation(storeDetails.getLocation());
        data.setOwnerName(storeDetails.getOwnerName());
        data.setPhoneNumber(storeDetails.getPhoneNumber());
        data.setStoreName(storeDetails.getStoreName());
        data.setStoreType(storeDetails.getStoreType());
      final StoreDetails updatedStoreData = repo.save(data);
          return ResponseEntity.ok(updatedStoreData);
      }	
   
   public Map<String, Boolean> deleteStore(@PathVariable(value = "storeId") Integer storeId){
       
	        StoreDetails  data = repo.getOne(storeId);
	            repo.delete(data);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("processed", Boolean.TRUE);
	        return response;
	    }

}