package com.cg.ora.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cg.ora.exception.FeedbackNotFoundException;
import com.cg.ora.exception.MechanicNotFoundException;
import com.cg.ora.exception.RequestNotFoundException;
import com.cg.ora.exception.UserNotFoundException;
import com.cg.ora.message.FeedbackMessage;
import com.cg.ora.message.MechanicMessage;
import com.cg.ora.message.Message;
import com.cg.ora.message.ServiceMessage;
import com.cg.ora.service.AdminService;

/**
 * The AdminController class implements a controller that simply request for
 * feedback,mechanic and user details from service interface and provides the
 * mechanism to allow or block mechanic using id.
 * @author prapti suraj
 * @since 2020-12-30
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	
  public static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	
	/**
	 * This is a method used to login with admin credentials 
	 * @param username to check if given parameter exists
	 * @param password to check if for given username the password is valid 
	 * @return ResponseEntity of type String  
	 */
	
	@PostMapping("/login")
	public ResponseEntity<Message> adminLogin(@Valid @RequestParam("email") String email,
			@RequestParam("password") String password) {
		LOGGER.info("Admin Login");
		Message message=new Message();
		//LOGGER.info("Admin Login");
		boolean login = adminService.loginAdmin(email, password);
		if (login) {
			message.setResMessage("Login Sucessful");
			//LOGGER.info("Admin Login Successful");
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} else {
			message.setResMessage("Login Unsucessful");
			//LOGGER.info("Admin Login Failed");
			return new ResponseEntity<Message>(message, HttpStatus.NOT_FOUND);
		}
	}	
	
	/**
	 * This is a method to display the list of feedbacks taken from users 
	 * @param none
	 * @return List of Feedback
	 * @throws FeedbackNotFoundException
	 */
	
	@GetMapping("/viewFeedback/{mechanicId}")
	public ResponseEntity<FeedbackMessage> viewFeedback(@Valid @PathVariable("mechanicId") int mechanicId) throws FeedbackNotFoundException {
		LOGGER.info("Viewing Feedback");
		FeedbackMessage viewFeedback = new FeedbackMessage();
		viewFeedback.setResMessage("Successfully viewed the feedback");
		viewFeedback.setFeedbackList(adminService.viewFeedback(mechanicId));
		return new ResponseEntity<FeedbackMessage>(viewFeedback, HttpStatus.OK);
	}
    
	/**
	 * This is a method display the list of mechanic
	 * @param none
	 * @return List of Mechanic
	 * @throws MechanicNotFoundException
	 */
	
	@GetMapping("/viewMechanic")
	public ResponseEntity<MechanicMessage> viewMechanicDetails() throws MechanicNotFoundException {
		LOGGER.info("Viewing mechanics");
       MechanicMessage mecMessage = new MechanicMessage();
       mecMessage.setResMessage("Successfully returning the list");
       mecMessage.setMechanicList(adminService.viewMechanicDetails());
		return new ResponseEntity<MechanicMessage>(mecMessage, HttpStatus.OK);
	}
    
	/**
	 * This is a method to display the list of users 
	 * @param none
	 * @return List of Users
	 * @throws UserNotFoundException
	 */
	
	@GetMapping("/viewUser")
	public ResponseEntity<Message> viewUserDetails() throws UserNotFoundException {

		LOGGER.info("Viewing Users");

		Message mgs=new Message();
		mgs.setResMessage("Successfully returning the list");
		mgs.setUserList(adminService.viewUserDetails());
		
		return new ResponseEntity<Message>(mgs, HttpStatus.OK);
	}
	
	/**
	 * This is a method to display the list of services
	 * @param none
	 * @return List of Services
	 * @throws RequestNotFoundException
	 */
	
	@GetMapping("/viewRequest")
	public ResponseEntity<ServiceMessage> viewRequestDetails() throws RequestNotFoundException {

		LOGGER.info("Viewing Users");

		ServiceMessage mgs=new ServiceMessage();
		mgs.setResMessage("Successfully returning the list");
		mgs.setServiceList(adminService.viewServiceDetails());
		return new ResponseEntity<ServiceMessage>(mgs, HttpStatus.OK);
	}
    
	/**
	 * This is a method to allow or block a mechanic according to ratings given by user in feedback
	 * @param mechanic id from URL. This parameter is used to know whether specific mechanic is blocked or not
	 * @return 
	 * @return ResponseEntity of type String
	 * @throws MechanicNotFoundException
	 */
	
	@GetMapping("/viewRatings/{mechanicId}")
	public ResponseEntity<Double> allowOrBlockMechanic(@PathVariable("mechanicId") int mechanicId)
			 {
		LOGGER.info("Allow and Block method started");
		double ratings = adminService.allowOrBlockMechanic(mechanicId);
		return new ResponseEntity<Double>(ratings, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/deleteUser/{id}")
	public ResponseEntity<Message> deleteUser(@PathVariable("id") int id) throws UserNotFoundException {

		LOGGER.info("Deleting Users");
		int deleteUser=adminService.deleteUser(id);
		if(deleteUser == 1)
		{
			return viewUserDetails();
		}
		else
		{
			return null;
		}
			
		
	}
	
	@DeleteMapping(value="/deleteMechanic/{id}")
	public ResponseEntity<MechanicMessage> deleteMechanic(@PathVariable("id") int id) throws MechanicNotFoundException {

		LOGGER.info("Deleting Mechanics");
		int deleteMechanic=adminService.deleteMechanic(id);
		if(deleteMechanic == 1)
		{
			return viewMechanicDetails();
		}
		else
		{
			return null;
		}
			
		
	}
	

}
