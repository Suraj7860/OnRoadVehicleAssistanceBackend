package com.cg.ora.message;


import java.util.List;

import com.cg.ora.model.UserModel;

public class Message {

	private String resMessage;
	
	private List<UserModel> userList;

	public String getResMessage() {
		return resMessage;
	}

	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}

	public List<UserModel> getUserList() {
		return userList;
	}

	public void setUserList(List<UserModel> userList) {
		this.userList = userList;
	}
	
	
}
