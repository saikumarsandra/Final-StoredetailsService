package com.cts.training.storedetails.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserModel {
	private Integer userId;


	private String userName;
	

	private String loginPassword;

	private String userType;



}
