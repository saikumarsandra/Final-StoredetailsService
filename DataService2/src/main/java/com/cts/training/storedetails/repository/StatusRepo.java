package com.cts.training.storedetails.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.training.storedetails.entity.Status;

@Repository
public interface StatusRepo extends JpaRepository<Status, Integer> {
	
	
	@Query("FROM Status str WHERE str.status='requested'")
	public List<Status> findStatusRequest();

	@Query("FROM Status sts WHERE sts.status='processed'")
	public List<Status> findprocessed();
	
      
}
