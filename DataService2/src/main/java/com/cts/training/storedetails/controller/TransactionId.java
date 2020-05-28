package com.cts.training.storedetails.controller;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.training.storedetails.DataServiceConfig;

@Component
public class TransactionId {
	
	@Autowired
	 DataServiceConfig properties;
	
	public String generateTransactionId(String transType) {
		long timestamp = System.nanoTime();
		String transId = transType + "_" + timestamp;
		MDC.clear();
		MDC.put("transactionId", transId);
		MDC.put("service", properties.getServiceName());
		return transId;
	}

}