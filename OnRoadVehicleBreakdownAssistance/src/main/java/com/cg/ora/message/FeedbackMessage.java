package com.cg.ora.message;

import java.util.List;

import com.cg.ora.model.Feedback;


public class FeedbackMessage {
	private String resMessage;
	
	private List<Feedback> feedbackList;

	public String getResMessage() {
	 	return resMessage;
	}

	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}
	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

	

	
	
}


