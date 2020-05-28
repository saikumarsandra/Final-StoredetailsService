package com.cts.training.storedetails.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.training.storedetails.entity.Retailer;
import com.cts.training.storedetails.entity.User;
@Repository
public interface RetailerRepository extends JpaRepository<Retailer, Integer> {

	
	List<Retailer> findByownerId(Integer ownerId);
	

	@Query("FROM Retailer owner WHERE ownerName=?1 AND loginPassword=?2")
	public Optional<Retailer> findByownerNameAndloginPassword(String ownerName,String loginPassword);

	
	
}
