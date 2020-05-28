package com.cts.training.storedetails.sftp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.training.storedetails.sftp.model.StoreDetails;

@Repository
public interface StoreRepository extends JpaRepository<StoreDetails, Integer> {

	
	
	List<StoreDetails> findByownerId(Integer ownerId);
}
