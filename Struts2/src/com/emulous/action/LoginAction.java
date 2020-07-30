package com.emulous.action;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	
	private static final String USER_ID = "ABC";
	private static final String PASSWORD = "123";

	private String userId;
	private String password;
	
	@Override
	public void validate() {
		if (StringUtils.isEmpty(getUserId())) {
			addFieldError("Id", "Id cannot be blank");
		}
		if (StringUtils.isEmpty(getPassword())) {
			addFieldError("password", "password cannot be blank");
		}
	}
	
	@Override
	public String execute() {
		if (getUserId().equals(USER_ID) && 	getPassword().equals(PASSWORD)) {
			return SUCCESS;
		}
		addFieldError("Please", "Enter the correct id or password");
		return LOGIN;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
