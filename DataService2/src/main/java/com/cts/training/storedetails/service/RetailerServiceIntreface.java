package com.cts.training.storedetails.service;

import java.util.List;

import com.cts.training.storedetails.entity.Retailer;
import com.cts.training.storedetails.entity.User;

public interface RetailerServiceIntreface {

	public List<Retailer> getall();
	public boolean saveRetailer(Retailer userInput);
	public Retailer findownerById(Integer ownerId) ;
  public boolean updateRetailer(Retailer userInput);
  
  public Retailer getOwnerByNameAndPassword(String ownerName,String loginPassword);
}