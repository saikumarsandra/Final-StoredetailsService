package com.cts.training.storedetails.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.training.storedetails.entity.User;
import com.cts.training.storedetails.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("FROM User user WHERE userName=?1 AND loginPassword=?2")
	public Optional<User> findByuserNameAndloginPassword(String userName,String loginPassword);

	
	
}
