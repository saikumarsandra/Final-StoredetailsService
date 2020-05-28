package com.cts.training.storedetails.sftp.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="storedetails2")
@ToString
public class StoreDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer storeId;
	
	@Column
	private Integer ownerId;

	@Column
	private String storeName;

	@Column
	private String phoneNumber;
	
	@Column
	private String location;
	@Column
	private String storeType;
	
	@Column
	private String ownerName;

}
