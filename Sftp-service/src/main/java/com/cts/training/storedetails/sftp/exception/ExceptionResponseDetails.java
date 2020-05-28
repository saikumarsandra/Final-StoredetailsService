package com.cts.training.storedetails.sftp.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@AllArgsConstructor
public class ExceptionResponseDetails {
	
	private Date timestamp;
	private String message;
	private String details;

	

}
