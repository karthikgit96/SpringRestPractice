package com.infyrail.dto;

public class ErrorMessage {
	private Integer errorCode;
	private String errorMessage;
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public ErrorMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorMessage(Integer errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	
}
