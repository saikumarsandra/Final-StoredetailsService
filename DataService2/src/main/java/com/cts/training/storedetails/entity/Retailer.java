package com.cts.training.storedetails.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ownerdetails")
@ToString
public class Retailer implements Serializable {
	private static final long serialVersionUID= 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ownerId;

	@Column
	private String ownerName;

	@Column
	private String ownerNumber;
	
	@Column
	private String loginPassword;
	
    @Column
    private String ownerType= "ROLE_ADMIN";


}
