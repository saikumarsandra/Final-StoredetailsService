package com.cts.training.storedetails.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cts.training.storedetails.entity.User;
import com.cts.training.storedetails.model.UserModel;
import com.cts.training.storedetails.repository.UserRepository;

@Service
public class UserService  implements UserServiceInterface{
	@Autowired
	private UserRepository userRepository;

	
	public List<User> getall(){
		List<User> records=this.userRepository.findAll();
		return records;
		
	}
	public Optional<User> getWithId(Integer userId){
		Optional<User> record= this.userRepository.findById(userId);
		return record;		
	}

	public boolean saveuser(UserModel user) {
		User data = new User();
		data.setUserName(user.getUserName());
		
		data.setLoginPassword(user.getLoginPassword());
		
		this.userRepository.save(data);
		
        
				return true;
	}

	public User getUserByNameAndPassword(String userName, String loginPassword) {
		
		return userRepository.findByuserNameAndloginPassword(userName, loginPassword).orElse(null);
	}
	
	
	
	
}
