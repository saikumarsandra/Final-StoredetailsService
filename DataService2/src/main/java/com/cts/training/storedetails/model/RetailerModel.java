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
public class RetailerModel {
	
	private Integer ownerId;
	
	private String ownerName;
	
	private String ownerNumber;
		
	private String loginPassword;
	
    private String ownerType;	
	
	

}
