package com.wook.app.utility.handler;

public class AdminAuthFailureHandler {

	private String defaultFailUrl;
	private String errorMessage;
	
	public String getDefaultFailUrl() {
		return defaultFailUrl;
	}
	public void setDefaultFailUrl(String defaultFailUrl) {
		this.defaultFailUrl = defaultFailUrl;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
