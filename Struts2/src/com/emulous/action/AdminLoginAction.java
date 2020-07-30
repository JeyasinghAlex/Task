package com.emulous.action;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public class AdminLoginAction extends ActionSupport{

	private static final String STAFF_ID = "LMN";
	private static final String PASSWORD = "678";
	private String adminId;
	private String pwd;
	
	@Override
	public void validate() {
		if (StringUtils.isEmpty(getAdminId())) {
			addFieldError("Id", "Id cannot be blank");
		}
		if (StringUtils.isEmpty(getPwd())) {
			addFieldError("password", "password cannot be blank");
		}
	}
	
	@Override
	public String execute() {
		if (getAdminId().equals(STAFF_ID) && 	getPwd().equals(PASSWORD)) {
			return SUCCESS;
		}
		addFieldError("Please", "Enter the correct id or password");
		return LOGIN;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}	
	
	
}
