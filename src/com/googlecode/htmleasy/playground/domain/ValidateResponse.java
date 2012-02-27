package com.googlecode.htmleasy.playground.domain;

public class ValidateResponse {

	public static ValidateResponse VALID = new ValidateResponse(true, "");
	
	private final boolean valid;
	private final String message;

	public ValidateResponse(boolean valid, String message) {
		this.valid = valid;
		this.message = message;
	}

	public boolean isValid() {
		return valid;
	}

	public String getMessage() {
		return message;
	}

}
