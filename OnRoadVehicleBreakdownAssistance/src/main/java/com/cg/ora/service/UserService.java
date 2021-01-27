package com.cg.ora.service;

import java.util.List;

import javax.validation.Valid;

import com.cg.ora.model.Feedback;
import com.cg.ora.model.Mechanic;
import com.cg.ora.model.Service;
import com.cg.ora.model.UserModel;
//This is an interface used for providing method declaration of UserServiceImpl class
public interface UserService {

	public String  giveFeedback(Feedback feedback,int mechanicId,int userId);

	public UserModel userRegistration(UserModel user);

	public UserModel updateUserById(UserModel user);

	public List<Service> addRequest(Service service);
	//public String addRequest(Service service);
	
	public List<Mechanic> searchMechanicByLocation(String location);
	
	public Service checkServiceExist(int userId);

	public UserModel getUserById(int id);

	public boolean loginUser(String email, String password);

	public boolean getUser(String email);

	public List<UserModel> viewUserByemail(@Valid String email);

	public List<Feedback> viewFeedback(int userId);

	public List<Service> viewRequestByUserId(int userId);

	public List<UserModel> viewMechanicById(@Valid int userId);

	//public List<Feedback> viewFeedback(int userId, @Valid int mechanicId);

}
