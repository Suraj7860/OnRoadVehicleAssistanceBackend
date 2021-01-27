package com.cg.ora.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ora.exception.MechanicNotFoundException;
import com.cg.ora.exception.UserNotFoundException;
import com.cg.ora.message.FeedbackMessage;
import com.cg.ora.message.Message;
import com.cg.ora.message.ServiceMessage;
import com.cg.ora.model.Feedback;
import com.cg.ora.model.Mechanic;
import com.cg.ora.model.Service;
import com.cg.ora.message.MechanicMessage;
import com.cg.ora.model.UserModel;
import com.cg.ora.service.UserService;

/**
 * This class implements a controller that simply allows user to give feedback
 * of mechanic,search mechanic, add request,add and update user details from
 * service interface
 * @author saifyn arfia
 * @since 2020-12-30
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;
	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * This is a method used to login with user credentials 
	 * @param email to check if given parameter exists
	 * @param password to check if for given emailid the password is valid 
	 * @return ResponseEntity of type String  
	 */
	
	@PostMapping("/userLogin")
	public ResponseEntity<Message> userLogin(@Valid  @RequestParam("email") String email,
			@RequestParam("password") String password) throws UserNotFoundException{
		Message msgobj=new Message();
		if(userService.getUser(email)) {
			
		LOGGER.info("User Login");
		boolean login = userService.loginUser(email, password);
		if (login) {
			LOGGER.info("User Login Successful");
			msgobj.setResMessage("Login Successful");
			msgobj.setUserList(userService.viewUserByemail(email));
			return new ResponseEntity<Message>(msgobj, HttpStatus.OK);
		} else {
			LOGGER.info("User Login Failed");
			msgobj.setResMessage("Login Failed");
			return new ResponseEntity<Message>(msgobj, HttpStatus.NOT_FOUND);
		}	
		}
		else {
			LOGGER.error("User does not exist");
			throw new UserNotFoundException("User not found");
		}
	}
	/**
	 * This is a method used to register the user
	 * @param UserModel object. It contains the entire user data to be added  
	 * @return ResponseEntity of type String
	 */
	
	@PostMapping("/userRegister")
	public ResponseEntity<Message> userRegistration(@Valid @RequestBody UserModel user) {
		Message msgobj=new Message();
		LOGGER.info("Adding user");
		UserModel addUser = userService.userRegistration(user);
		if (addUser == null) {
			msgobj.setResMessage("User not added");
			return new ResponseEntity<Message>(msgobj,HttpStatus.NOT_FOUND);
		}
		msgobj.setResMessage("User Registered Sucessfully");
		msgobj.setUserList(userService.viewUserByemail(user.getUserEmailId()));
		return new ResponseEntity<Message>(msgobj , HttpStatus.OK);
	}
	/**
	 * This is a method used to give feedback to the mechanic
	 * @param mechanic id from URL to a specific mechanic to which feedback is to be given
	 * @param user id from URL to given feedback to mechanic
	 * @param feedback object. It contains the feedback data given by user 
	 * @return ResponseEntity of type String
	 */
	
	@PostMapping("/giveFeedback/{mechanicId}/{userId}")
    public ResponseEntity<FeedbackMessage> giveFeedback(@Valid @PathVariable("mechanicId") int mechanicId,
            @PathVariable("userId") int userId, @RequestBody Feedback feedback) {
        LOGGER.info("Giving Feedback to mechanic");
        FeedbackMessage msgobj=new FeedbackMessage();
        String giveFeedback = userService.giveFeedback(feedback, mechanicId, userId);
        if (giveFeedback == null) {
        	msgobj.setResMessage("Feedback not added");
            return new ResponseEntity<FeedbackMessage>(msgobj, HttpStatus.NOT_FOUND);
        }
        msgobj.setResMessage(giveFeedback);
        return new ResponseEntity<FeedbackMessage>(msgobj, HttpStatus.OK);
    }


//	@PostMapping("/giveFeedback/{mechanicId}/{userId}")
//	public ResponseEntity<FeedbackMsg> giveFeedback(@Valid @PathVariable("mechanicId") int mechanicId,
//			@PathVariable("userId") int userId, @RequestBody Feedback feedback) {
//		LOGGER.info("Giving Feedback to mechanic");
//		FeedbackMsg msgobj=new FeedbackMsg();
//		
//		String retFeed=userService.giveFeedback(feedback, mechanicId, userId);
//		LOGGER.info(" Feedback to mechanic completeed");
//		msgobj.setResMessage(retFeed);
//		 
//		return new ResponseEntity<FeedbackMsg>(msgobj, HttpStatus.OK);
//	}
    
	/**
	 * This is a method used to add request to a mechanic
	 * @param Service object This parameter contains the entire request data sent by user to a specific mechanic  
	 * @return ResponseEntity of type String
	 */
	
	@PostMapping("/addRequest")
	public ResponseEntity<ServiceMessage> addRequest(@Valid @RequestBody Service service) {
		ServiceMessage msgobj=new ServiceMessage();
		LOGGER.info("Adding Request");
		msgobj.setResMessage("Service request sent");
		msgobj.setServiceList( userService.addRequest(service));
		System.out.print(msgobj);
		LOGGER.info("Request added");
		return new ResponseEntity<ServiceMessage>(msgobj, HttpStatus.OK);
}
//	@PostMapping("/addRequest")
//    public ResponseEntity<ServiceMsg> addRequest(@Valid @RequestBody Service service) {
//        LOGGER.info("Adding Request");
//        ServiceMsg msgobj=new ServiceMsg();
//        String addRequest = userService.addRequest(service);
//        msgobj.setResMessage(addRequest);
//        LOGGER.info("Adding Request added  successfully");
//        return new ResponseEntity<ServiceMsg>(msgobj, HttpStatus.OK);
//    }
	
    
	/**
	 * This is a method used to update the user by id
	 * @param UserModel object .This parameter is used to pass the User data for updating the details
	 * @return ResponseEntity of type String
	 * @throws UserNotFoundException
	 */
	
	@PutMapping("/updateUser")
	public ResponseEntity<Message> updateUserById(@Valid @RequestBody UserModel user) throws UserNotFoundException {
		Message msgobj=new Message();
		LOGGER.info("Update user");
		if (userService.getUserById(user.getUserId()) != null) {
			userService.updateUserById(user);
			msgobj.setResMessage("User updated successfully");
			msgobj.setUserList(userService.viewUserByemail(user.getUserEmailId()));
			return new ResponseEntity<Message>(msgobj, HttpStatus.OK);
		} else {
			String error="User cannot be updated, as id " + user.getUserId() + " not present";
			LOGGER.error(error);
			throw new UserNotFoundException("User could not be updated,as id " + user.getUserId() + " not present");
		}
	}
    
	/**
	 * This is a method to search a mechanic by location and display the list of mechanic
	 * @param location of type String. This parameter is used by user to enter location so it will show the nearby mechanics available  
	 * @return List of Mechanic
	 * @throws MechanicNotFoundException
	 */	
	
	@GetMapping("/searchMechanic/{location}")
	public ResponseEntity<MechanicMessage> searchMechanic(@Valid @PathVariable("location") String location)
			throws MechanicNotFoundException {
		MechanicMessage msgobj=new MechanicMessage();
		LOGGER.info("Searching Mechanic by location");
		List<Mechanic> mechanicList = userService.searchMechanicByLocation(location);
		if (mechanicList.isEmpty()) {
			msgobj.setResMessage("Mechanic does not exist at ");
			throw new MechanicNotFoundException("msgobj");
		}
		msgobj.setMechanicList(mechanicList);
		msgobj.setResMessage("Mechanich searched");
		return new ResponseEntity<MechanicMessage>(msgobj, HttpStatus.OK);
	}
	@GetMapping("/viewUser/{userId}")
	public ResponseEntity<Message> viewUser(@Valid @PathVariable("userId") int userId)
			throws MechanicNotFoundException {
		Message msgobj=new Message();
		msgobj.setResMessage(" User List");
		msgobj.setUserList(userService.viewMechanicById(userId));
		return new ResponseEntity<Message>(msgobj, HttpStatus.OK);
		
		
	}

}
