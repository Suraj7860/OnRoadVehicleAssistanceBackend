package com.cg.ora.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

/**
 * This class is an entity class for Service. It contains all the details about Service body
 * @author prapti suraj
 * @since 2020-12-28
 */

@Entity
@Table(name = "Service")
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Service_Id")
	private int serviceId;
	
	@Column(name = "Service_Type")
	@NotNull
	@Size(min = 5, max = 40, message = "Service Type must be between 5 and 40 characters")
	private String serviceType;

	@NotNull
	@Column(name = "UserId")
	@Min(value = 1, message = "UserId cannot be 0")
	private int userId;
	
	@NotNull
	@Column(name = "MechanicId")
	@Min(value = 1, message = "MechanicId cannot be 0")
	private int mechanicId;
	
	@Size(min = 5, max = 40, message = "Location must be between 5 and 40 characters")
	@Column(name = "Service_location")
	private String location;
	
	@OneToOne(mappedBy="service",cascade = CascadeType.ALL)
	private Feedback feedback;

	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Service(int serviceId,
			@Size(min = 5, max = 40, message = "Service Type must be between 5 and 40 characters") String serviceType,
			@Min(value = 1, message = "UserId cannot be 0") int userId,
			@Min(value = 1, message = "MechanicId cannot be 0") int mechanicId,
			@Size(min = 5, max = 40, message = "Location must be between 5 and 40 characters") String location,
			Feedback feedback) {
		super();
		this.serviceId = serviceId;
		this.serviceType = serviceType;
		this.userId = userId;
		this.mechanicId = mechanicId;
		this.location = location;
		this.feedback = feedback;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMechanicId() {
		return mechanicId;
	}

	public void setMechanicId(int mechanicId) {
		this.mechanicId = mechanicId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "Service [serviceId=" + serviceId + ", serviceType=" + serviceType + ", userId=" + userId
				+ ", mechanicId=" + mechanicId + ", location=" + location + ", feedback=" + feedback + "]";
	}
	
	
}
