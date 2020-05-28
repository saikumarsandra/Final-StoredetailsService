package com.cts.training.storedetails.sftp.exception;

public class ServerDownException extends RuntimeException {

	private static final long serialVersionUID = 5854724800214739570L;

	public ServerDownException(String message) {
		super(message);
	}
}
