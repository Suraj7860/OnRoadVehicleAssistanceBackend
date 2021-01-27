package com.cg.ora.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * This class is an entity class for Feedback. It contains all the details about Feedback body
 * @author prapti suraj
 * @since 2020-12-28
 */

@Entity
@Table(name="feedback")
public class Feedback {	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="feedbackId")
	private int feedbackId;
	
	@Min(value = 1, message = "UserId cannot be 0")
	@Column(name="userId")
	private int userId;
	
//	@Min(value = 1, message = "MechanicId cannot be 0")	
//	@Column(name="mechanicId")
//	private int mechanicId;
	
	@NotNull
	@Size(min = 3, max = 40, message 
    = "feedback must be between 3 and 40 characters")
	@Column(name="feedback")
	private String feedback;
	
	@Min(value = 0, message = "Ratings not given")
    @Max(value = 5, message = "Ratings can be given from 1 to 5 only ")
	@Column(name="ratings")
	private float ratings;
	
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "mechanicId"), name = "mechanicId")
	@JsonIgnore
	private Mechanic mechanic;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "serviceId"), name = "serviceId")
	@JsonIgnore
	private Service service;

	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feedback(int feedbackId, @Min(value = 1, message = "UserId cannot be 0") int userId,
			@NotNull @Size(min = 3, max = 40, message = "feedback must be between 3 and 40 characters") String feedback,
			@Min(value = 0, message = "Ratings not given") @Max(value = 5, message = "Ratings can be given from 1 to 5 only ") float ratings,
			Mechanic mechanic, Service service) {
		super();
		this.feedbackId = feedbackId;
		this.userId = userId;
		this.feedback = feedback;
		this.ratings = ratings;
		this.mechanic = mechanic;
		this.service = service;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public float getRatings() {
		return ratings;
	}

	public void setRatings(float ratings) {
		this.ratings = ratings;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", userId=" + userId + ", feedback=" + feedback + ", ratings="
				+ ratings + ", mechanic=" + mechanic + ", service=" + service + "]";
	}

	
}
	