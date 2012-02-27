package com.googlecode.htmleasy.playground.domain;

import javax.ws.rs.FormParam;

/**
 * Simple POJO to store user signup form information.
 * 
 */
public class SignupDetails {
	
	public static enum Sex {Male, Female, Unknown};

	@FormParam("username")
	private String username = "";

	@FormParam("password")
	private String password = "";
	
	@FormParam("confirmPassword")
	private String confirmPassword = "";
	
	@FormParam("sex")
	private Sex sex = Sex.Unknown;
	
	@FormParam("agreedLegal")
	private boolean agreedLegal;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isAgreedLegal() {
		return agreedLegal;
	}

	public void setAgreedLegal(boolean agreedLegal) {
		this.agreedLegal = agreedLegal;
	}
	
}
