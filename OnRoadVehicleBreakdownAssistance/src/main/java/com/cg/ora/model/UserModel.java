package com.cg.ora.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class is an entity class for User. It contains all the details about User body
 * @author saifyn arfia
 * @since 2020-12-28
 */

	@Entity
	@Table(name="UserInfo")
	public class UserModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="userid")
	private int userId;
	
    @NotNull
    @Size(min = 1, message 
    = "User name must ne between 5 and 15 characters")
	@Column (name="username")
	private String userName;
	
	
    @NotNull
    @Digits(integer=10,fraction=0)
	@Column (name="userphonenumber")
	private BigInteger userPhoneNumber;
	
	
	@Email(message="Email id is not valid")
	@NotNull
	@Column (name="useremailid",unique=true)
	private String userEmailId;
	
	@NotNull
	@Size(min = 5, max = 8, message 
    = "Password must ne between 5 and 8 characters")
	@Column (name="userpassword")
	private String userPassword;
	
	
	public UserModel(int userId,String uname,String upass,String uemail,BigInteger phone){
		this.userId=userId;
		this.userName=uname;
		this.userPassword=upass;
		this.userEmailId=uemail;
		this.userPhoneNumber=phone;
		
		
			
			
		}

	public UserModel() {
		super();
		
	}

	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
	

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public BigInteger getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(BigInteger userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPhoneNumber=" + userPhoneNumber
				+ ", userEmailId=" + userEmailId + ", userPassword=" + userPassword + "]";
	}

	
	}	
	
	
