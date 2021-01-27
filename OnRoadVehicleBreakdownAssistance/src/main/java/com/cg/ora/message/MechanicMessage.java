package com.cg.ora.message;

import java.util.List;

import com.cg.ora.model.Mechanic;



public class MechanicMessage {
	private String resMessage;
	
	private List<Mechanic> mechanicList;

	public String getResMessage() {
	 	return resMessage;
	}

	public void setResMessage(String resMessage) {
		this.resMessage = resMessage;
	}

	public List<Mechanic> getMechanicList() {
		return mechanicList;
	}

	public void setMechanicList(List<Mechanic> mechanicList) {
		this.mechanicList = mechanicList;
	}

	

}
