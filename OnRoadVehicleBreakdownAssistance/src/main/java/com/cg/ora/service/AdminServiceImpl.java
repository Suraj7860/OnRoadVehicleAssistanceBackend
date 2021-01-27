package com.cg.ora.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.ora.model.Feedback;
import com.cg.ora.model.Mechanic;
import com.cg.ora.model.UserModel;
import com.cg.ora.repository.FeedbackRepository;
import com.cg.ora.repository.MechanicRepository;
import com.cg.ora.repository.ServiceRepository;
import com.cg.ora.repository.UserRepository;

/**
 * This class implements a service interface that simply 
 * consist of repositories for feedback,mechanic and user and contains business logic
 * to display details of feedback,mechanic and user.
 * and provides the mechanism to allow or block mechanic using id.
 * @author prapti suraj
 * @since 2020-12-30
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	@Autowired
	FeedbackRepository feedbackRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	MechanicRepository mechanicRepository;
	
	@Autowired
	ServiceRepository serviceRepository;
	public static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
   
	/**
	 * This is a method used to return feedback details
	 * @param mechanic id. This parameter helps to find feedback data according to specific mechanic 
	 * @return List of Feedback
	 */
	@Override
	public List<Feedback> viewFeedback(int mecId) {
		LOGGER.info("Viewing feedback by mechanic id");
		 Mechanic m=mechanicRepository.getOne(mecId);
		 List<Feedback> list=feedbackRepository.findByMechanic(m);
		return list;
	}
	
    
	/**
	 * This is a method used to return user details
	 * @return List of User
	 */
	@Override
	public List<UserModel> viewUserDetails() {
		LOGGER.info("Viewing User Details");
		return userRepository.findAll();
	}
    
	/**
	 * This is a method used to return mechanic details
	 * @return List of Mechanic
	 */
	@Override
	public List<Mechanic> viewMechanicDetails() {
		LOGGER.info("Viewing Mechanic Details");
		return mechanicRepository.findAll();
	}
	
	/**
	 * This is a method used to return service details
	 * @return List of Service
	 */
	@Override
	public List<com.cg.ora.model.Service> viewServiceDetails() {
		LOGGER.info("Viewing Mechanic Details");
		return serviceRepository.findAll();
	}
    
	/**
	 * This is a method used to allow and block mechanic according to ratings
	 * @param mechanic id to specify a mechanic according to id
	 * @return String
	 */
	@Override
	public double allowOrBlockMechanic(int mechanicId) {
		double avgRating=0;
		if (mechanicRepository.existsById(mechanicId)) {
			LOGGER.info("Allowing and blocking mechanic");
			Double sumOfRatings = feedbackRepository.sumOfRatings(mechanicId);
			if (sumOfRatings == null) {
				return 0;
			}

			Long countOfRatings = feedbackRepository.countOfRatings(mechanicId);

			avgRating = sumOfRatings / countOfRatings;
            
		}
		return avgRating;
	}
     
	/**
	 * This is a method used to send admin login credentials 
	 * @param username to check if given parameter exists
	 * @param password to check if for given username the password is valid 
	 * @return boolean
	 */
	
	@Override
	public boolean loginAdmin(String email, String password) {
		if(email.equals("admin123@gmail.com") && password.equals("Admin123")) {
			return true;
		}
		else {
		return false;
		}
	}
	@Override
	public int deleteUser(int userId)
	{
		int deleteUser=0;
		UserModel user= userRepository.getOne(userId);
		if(user !=null)
		{
			userRepository.delete(user);
			deleteUser=1;
		}
		else
		{
			deleteUser=0;
		}
		return deleteUser;
	}
	@Override
	public int deleteMechanic(int mechanicId)
	{
		int deleteMechanic=0;
		Mechanic mechanic= mechanicRepository.getOne(mechanicId);
		if(mechanic !=null)
		{
			mechanicRepository.delete(mechanic);
			deleteMechanic=1;
		}
		else
		{
			deleteMechanic=0;
		}
		return deleteMechanic;
	}


}
