package com.lms.gateway.exception;

public class GatewayException extends Exception {

	private String errorCode;

	public GatewayException(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}