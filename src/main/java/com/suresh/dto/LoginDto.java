package com.suresh.dto;

public class LoginDto {

	private String userOrEmail;
	private String password;

	public LoginDto() {
		// TODO Auto-generated constructor stub
	}

	public String getUserOrEmail() {
		return userOrEmail;
	}

	public void setUserOrEmail(String userOrEmail) {
		this.userOrEmail = userOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
