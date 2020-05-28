package com.cts.training.storedetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.training.storedetails.entity.Status;
import com.cts.training.storedetails.repository.StatusRepo;

@Service
public class StatusService {

	@Autowired
	public StatusRepo repo;
	
	
    public void StatusReciever()
 	{
 		List<Status> getstatus=this.repo.findStatusRequest();
 		for(Status str:getstatus)
 		{
 				Status store=new Status(str.getId(),"processed");
 				this.repo.save(store);
 		}
 	}
	
	
	public  void  ProcessedStatus() {
		List<Status>  getStatus  = this.repo.findprocessed();
		for ( Status str :getStatus) {
			Status data = new Status(str.getId(),"requested");
			this.repo.save(data);
			
		}
}
}
