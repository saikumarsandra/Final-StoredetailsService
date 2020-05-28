package com.cts.training.storedetails.sftp.RestClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.cts.training.storedetails.sftp.config.SftpStoreConfiguration;
import com.cts.training.storedetails.sftp.model.RetailerModel;
import com.cts.training.storedetails.sftp.model.UserModel;
@Component
public class CommonRestClient {
	@Autowired
	SftpStoreConfiguration properties;
	
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(properties.getUsername(), properties.getPassword());
		return headers;
	}
	
	//retailer detailes with headers
	public HttpEntity<RetailerModel> getOwnerHttpEntity(){
		return new HttpEntity<RetailerModel>(getHeaders()); 
	}
	
	public HttpEntity<RetailerModel> postownerHttpEntity(RetailerModel owner){
		return new HttpEntity<RetailerModel>(owner ,getHeaders()); 
	}
	
	//user with headers
	public HttpEntity<UserModel> getUserHttpEntity(){
		return new HttpEntity<UserModel>(getHeaders()); 
	}
	public HttpEntity<UserModel> postUserHttpEntity(UserModel user){
		return new HttpEntity<UserModel>(user,getHeaders()); 
	}
	
	
}

