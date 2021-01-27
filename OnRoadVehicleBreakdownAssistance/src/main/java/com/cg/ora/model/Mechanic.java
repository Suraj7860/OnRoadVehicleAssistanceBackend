package com.cg.ora.model;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class is an entity class for Mechanic. It contains all the details about Mechanic body
 * @author ashwini ruskikesh
 * @since 2020-12-28
 */

@Entity
@Table(name="Mechanic")
public class Mechanic {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mechanicid")
	private int mechanicId;
	
	@NotNull
	@Size(min = 1, message 
    = "Mechanic name must ne between 5 and 15 characters")
	@Column(name="mechanicname")
	private String mechanicName;
		
//	@NotNull
//	@Size(min=10,max=10,message="wrong phone number")
	@Column(name="mechanicphonenumber")
	private BigInteger mechanicPhoneNumber;
   
	
	@NotNull
	@Size(min = 1, message 
    = "Service type must ne between 5 and 15 characters")
	@Column(name="mechanicservicetype")
	private String mechanicServiceType;

	@Email
	@NotNull
	@Column(name="mechanicemailid",unique=true)
	private String mechanicEmailId;
		
	@NotNull
	@Size(min = 5, max = 8, message 
    = "Password must ne between 5 and 8 characters")
	@Column(name="mechanicpassword")
	private String mechanicPassword;
	
	@NotNull
	@Size(min = 1, message 
    = "Location must ne between 5 and 15 characters")
	@Column(name="location")
	private String location;
	
	@OneToMany(mappedBy="mechanic",cascade = CascadeType.ALL)
	private List<Feedback> feedback;

	public Mechanic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mechanic(int mechanicId,
			@NotNull @Size(min = 1, message = "Mechanic name must ne between 5 and 15 characters") String mechanicName,
			@NotNull @Size(min = 10, max = 10, message = "wrong phone number") BigInteger mechanicPhoneNumber,
			@NotNull @Size(min = 1, message = "Service type must ne between 5 and 15 characters") String mechanicServiceType,
			@Email @NotNull String mechanicEmailId,
			@NotNull @Size(min = 5, max = 8, message = "Password must ne between 5 and 8 characters") String mechanicPassword,
			@NotNull @Size(min = 1,message = "Location must ne between 5 and 15 characters") String location,
			List<Feedback> feedback) {
		super();
		this.mechanicId = mechanicId;
		this.mechanicName = mechanicName;
		this.mechanicPhoneNumber = mechanicPhoneNumber;
		this.mechanicServiceType = mechanicServiceType;
		this.mechanicEmailId = mechanicEmailId;
		this.mechanicPassword = mechanicPassword;
		this.location = location;
		this.feedback = feedback;
	}

	public int getMechanicId() {
		return mechanicId;
	}

	public void setMechanicId(int mechanicId) {
		this.mechanicId = mechanicId;
	}

	public String getMechanicName() {
		return mechanicName;
	}

	public void setMechanicName(String mechanicName) {
		this.mechanicName = mechanicName;
	}

	public BigInteger getMechanicPhoneNumber() {
		return mechanicPhoneNumber;
	}

	public void setMechanicPhoneNumber(BigInteger mechanicPhoneNumber) {
		this.mechanicPhoneNumber = mechanicPhoneNumber;
	}

	public String getMechanicServiceType() {
		return mechanicServiceType;
	}

	public void setMechanicServiceType(String mechanicServiceType) {
		this.mechanicServiceType = mechanicServiceType;
	}

	public String getMechanicEmailId() {
		return mechanicEmailId;
	}

	public void setMechanicEmailId(String mechanicEmailId) {
		this.mechanicEmailId = mechanicEmailId;
	}

	public String getMechanicPassword() {
		return mechanicPassword;
	}

	public void setMechanicPassword(String mechanicPassword) {
		this.mechanicPassword = mechanicPassword;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "Mechanic [mechanicId=" + mechanicId + ", mechanicName=" + mechanicName + ", mechanicPhoneNumber="
				+ mechanicPhoneNumber + ", mechanicServiceType=" + mechanicServiceType + ", mechanicEmailId="
				+ mechanicEmailId + ", mechanicPassword=" + mechanicPassword + ", location=" + location + ", feedback="
				+ feedback + "]";
	}	

	
	
	
	
	

}