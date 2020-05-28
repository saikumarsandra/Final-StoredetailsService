package com.cts.training.storedetails.service;

import java.util.List;
import java.util.Optional;

import com.cts.training.storedetails.entity.User;
import com.cts.training.storedetails.model.UserModel;

public interface UserServiceInterface {

	public List<User> getall();
	public Optional<User> getWithId(Integer userId);
	public boolean saveuser(UserModel user);
	public User getUserByNameAndPassword(String userName,String loginPassword);

}
