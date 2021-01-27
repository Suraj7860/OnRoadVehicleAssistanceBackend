package com.cg.ora.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.ora.model.Feedback;
import com.cg.ora.model.Mechanic;
import com.cg.ora.model.UserModel;

//This is an interface used for providing method declaration of AdminServiceImpl class
@Service
public interface AdminService {
	public List<Feedback> viewFeedback(int mechanicId);
	public List<UserModel> viewUserDetails();
	public List<Mechanic> viewMechanicDetails();
	public List<com.cg.ora.model.Service> viewServiceDetails();
	public double allowOrBlockMechanic(int mechanicId);
	public boolean loginAdmin(String username, String password);
	public int deleteUser(int userId);
	public int deleteMechanic(int mechanicId);

}
