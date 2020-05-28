package com.cts.training.storedetails.sftp.service.datarestservice;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.cts.training.storedetails.sftp.RestClient.CommonRestClient;
import com.cts.training.storedetails.sftp.config.SftpStoreConfiguration;
import com.cts.training.storedetails.sftp.model.RetailerModel;
import com.cts.training.storedetails.sftp.model.UserModel;

@Configuration
public class DataRestService {
	
	@Autowired
   // @LoadBalanced
	RestTemplate restTemplate;
	
	@Autowired
	SftpStoreConfiguration properties;
	
	@Autowired
	CommonRestClient restClient;
	
// save retailer
		
	public ResponseEntity<RetailerModel>postOwnerData(RetailerModel owner) {
		return  restTemplate.exchange("http://localhost:8585/saveretailer", HttpMethod.POST,
			 restClient.postownerHttpEntity(owner), RetailerModel.class);
		
	}
// get retailer details
	@SuppressWarnings("serial")
	public ResponseEntity<RetailerModel> getOwnerByNameAndPassword(String ownerName, String loginPassword) {
		return restTemplate.exchange("http://localhost:8585/owner/{ownerName}/{loginPassword}", HttpMethod.GET,
				restClient.getOwnerHttpEntity(), RetailerModel.class,
				new HashMap<String,String>(){
					{
						put("ownerName", ownerName);
						put("loginPassword",loginPassword);
					}
				});
	}
	
	// save user
			
		public ResponseEntity<UserModel>postUserData(UserModel user) {
			return  restTemplate.exchange("http://localhost:8585/saveUser", HttpMethod.POST,
				 restClient.postUserHttpEntity(user), UserModel.class);
			
		}
	// get user details
		@SuppressWarnings("serial")
		public ResponseEntity<UserModel> getUserByNameAndPassword(String userName, String loginPassword) {
			return restTemplate.exchange("http://localhost:8585/users/{userName}/{loginPassword}", HttpMethod.GET,
					restClient.getUserHttpEntity(), UserModel.class,
					new HashMap<String,String>(){
					
						{
							put("userName", userName);
							put("loginPassword",loginPassword);
						}
					});
		}

}
