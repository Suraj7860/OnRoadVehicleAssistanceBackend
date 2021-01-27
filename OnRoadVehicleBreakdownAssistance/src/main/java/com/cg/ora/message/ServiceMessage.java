package com.cg.ora.message;

import java.util.List;
import com.cg.ora.model.Service;

public class ServiceMessage {
	    private String resMessage;
		
		private List<Service> serviceList;

		public String getResMessage() {
		 	return resMessage;
		}

		public void setResMessage(String resMessage) {
			this.resMessage = resMessage;
		}

		public List<Service> getServiceList() {
			return serviceList;
		}

		public void setServiceList(List<Service> serviceList) {
			this.serviceList = serviceList;
		}

		
		
	}


