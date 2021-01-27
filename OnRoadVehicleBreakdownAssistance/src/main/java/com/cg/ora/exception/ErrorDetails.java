package com.cg.ora.exception;

import java.util.Date;

/**
 * This class is an entity class for Error Details
 * @author prapti suraj
 * @since 2020-12-31
 */

public class ErrorDetails {
	private Date timeStamp;
	private String message;	
	private String details;
	public ErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorDetails(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "ErrorDetails [timeStamp=" + timeStamp + ", message=" + message + ", details=" + details + "]";
	}
	
	
	
	

}
