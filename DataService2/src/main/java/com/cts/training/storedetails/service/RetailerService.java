package com.cts.training.storedetails.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.training.storedetails.entity.Retailer;
import com.cts.training.storedetails.entity.User;
import com.cts.training.storedetails.model.RetailerListModel;
import com.cts.training.storedetails.repository.RetailerRepository;

@Service

public class RetailerService implements RetailerServiceIntreface {

	@Autowired
	private RetailerRepository retailerRepository;
	
	
	public List<Retailer> getall() {
		
		return  retailerRepository.findAll();
	}


	public boolean saveRetailer(Retailer userInput) {
		 Retailer data = new Retailer();
		 data.setOwnerName(userInput.getOwnerName());
		 data.setOwnerNumber(userInput.getOwnerNumber());
		 data.setLoginPassword(userInput.getLoginPassword());

		 this.retailerRepository.save(data);
	    
		
		return true;
	}
	
	public Retailer findownerById(Integer ownerId) {
	
		Optional<Retailer> retailer = retailerRepository.findById(ownerId);
		
		Retailer user = retailer.orElse(null);
		return user;
	
		
	}
 public boolean updateRetailer(Retailer userInput) {
	 
	 Retailer data = new Retailer();
	 data.setOwnerName(userInput.getOwnerName());
	 data.setOwnerNumber(userInput.getOwnerNumber());
	 data.setLoginPassword(userInput.getLoginPassword());

	 this.retailerRepository.save(data);
	 
	  return true;
 }
 public Retailer getOwnerByNameAndPassword(String ownerName, String loginPassword) {
		
		return retailerRepository.findByownerNameAndloginPassword(ownerName, loginPassword).orElse(null);
	}



	
	
}
	


	


