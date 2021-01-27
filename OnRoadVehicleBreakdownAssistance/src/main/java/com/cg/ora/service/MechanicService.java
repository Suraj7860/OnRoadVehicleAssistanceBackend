package com.cg.ora.service;

import java.util.List;

import javax.validation.Valid;

import com.cg.ora.model.Feedback;
import com.cg.ora.model.Mechanic;
import com.cg.ora.model.Service;

//This is an interface used for providing method declaration of MechanicServiceImpl class
public interface MechanicService {
	public Mechanic addMechanic(Mechanic mechanic);

	public List<Service> viewRequest(int mechanicId);

	public List<Feedback> viewFeedback(int mechanicId);

	public boolean loginMechanic(String email, String password);

	public boolean getMechanic(String email);

	public List<Mechanic> viewMechanic(int mechanicId);

	public List<Mechanic> viewMechanicByEmailId(@Valid String email);

}
