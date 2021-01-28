package com.cg.ora.controller;

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

import com.cg.ora.exception.FeedbackNotFoundException;
import com.cg.ora.exception.MechanicNotFoundException;
import com.cg.ora.exception.RequestNotFoundException;
import com.cg.ora.message.FeedbackMessage;
import com.cg.ora.message.MechanicMessage;
import com.cg.ora.message.Message;
import com.cg.ora.message.ServiceMessage;
import com.cg.ora.model.Mechanic;
import com.cg.ora.service.MechanicService;

/**
 * This class implements a controller that simply allows mechanic to register ,
 * view feedback according to mechanic id and view request from service
 * interface
 * @author ashwini rushikesh
 * @since 2020-12-30
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/mechanic")
public class MechanicController {
	@Autowired
	MechanicService mechanicService;
	
	 public static final Logger  LOGGER= LoggerFactory.getLogger(MechanicController.class);
	

	/**
	 * This is a method used to login with mechanic credentials 
	 * @param email to check if given parameter exists
	 * @param password to check if for given email the password is valid 
	 * @return ResponseEntity of type String  
	 * @throws MechanicNotFoundException
	 */
	
	@PostMapping("/mechanicLogin")
	public ResponseEntity<MechanicMessage> mechanicLogin(@Valid @RequestParam("email") String email,
			@RequestParam("password") String password){
		MechanicMessage msgobj=new MechanicMessage();
		
		LOGGER.info("Mechanic Login");
		if(mechanicService.getMechanic(email)) {
			LOGGER.info("Mechanic Login");
			
			msgobj.setMechanicList(mechanicService.viewMechanicByEmailId(email));
			boolean login = mechanicService.loginMechanic(email, password);
			if (login) {
				LOGGER.info("Mechanic Login Successful");
				msgobj.setResMessage("Mechanic Login Successful");
				msgobj.setMechanicList(mechanicService.viewMechanicByEmailId(email));
				return new ResponseEntity<MechanicMessage>(msgobj, HttpStatus.OK);
			} else {
				LOGGER.info("Mechanic Login Failed");
				msgobj.setResMessage("Mechanic Login Failed");
				return new ResponseEntity<MechanicMessage>(msgobj, HttpStatus.NOT_FOUND);
			}
			}
			else {
				LOGGER.error("Mechanic does not exist");
				return new ResponseEntity<MechanicMessage>(msgobj, HttpStatus.NOT_FOUND);
			}
	}
	/**
	 * This is a method used to register the mechanic
	 * @param Mechanic object . This parameter will contain the entire data about the new mechanic to be added
	 * @return ResponseEntity of type String
	 */
	@PostMapping("/addMechanic")
	public ResponseEntity<MechanicMessage> addMechanic(@Valid @RequestBody Mechanic mechanic) {
		LOGGER.info("Adding Mechanic");
		MechanicMessage msgobj=new MechanicMessage();
		Mechanic addMechanic = mechanicService.addMechanic(mechanic);
		if (addMechanic == null) {
			msgobj.setResMessage("Mechanic Not Registerd");
			return new ResponseEntity<MechanicMessage>(msgobj, HttpStatus.NOT_FOUND);
		}
		msgobj.setResMessage("Mechanic Registerd");
		msgobj.setMechanicList(mechanicService.viewMechanic(mechanic.getMechanicId()));
		return new ResponseEntity<MechanicMessage>(msgobj, HttpStatus.OK);
	}
    
	
	/**
	 * This is a method to display the list of feedbacks taken from users according to mechanic id
	 * @param mechanic id from URL. This parameter will help to view feedback according to specific mechanic id
	 * @return List of Feedback
	 * @throws FeedbackNotFoundException
	 */
	@GetMapping("/viewFeedback/{mechanicId}")
	public ResponseEntity<FeedbackMessage> viewFeedback(@Valid @PathVariable("mechanicId") int mechanicId) throws FeedbackNotFoundException {
		LOGGER.info("Viewing feedback according to mechanic id ");
		FeedbackMessage msgobj=new FeedbackMessage();
		msgobj.setResMessage("returning feedbackList");
		msgobj.setFeedbackList(mechanicService.viewFeedback(mechanicId));
		return new ResponseEntity<FeedbackMessage>(msgobj, HttpStatus.OK);
	}
    
	/**
	 * This is a method to display the list of request from users to the specific mechanic 
	 * @param mechanic id from URL. This parameter will help us to view request sent to a specific mechanic
	 * @return List of Request
	 * @throws RequestNotFoundException
	 */
	
	@GetMapping("/viewRequest/{mechanicId}")

	public ResponseEntity<ServiceMessage> viewRequest(@PathVariable("mechanicId") int mechanicId) throws RequestNotFoundException{
		LOGGER.info("Viewing Request according to mechanic id");
//		List<Service> viewRequest = mechanicService.viewRequest(mechanicId);
		ServiceMessage msgobj=new ServiceMessage();
		msgobj.setResMessage("Returning request list");
		msgobj.setServiceList(mechanicService.viewRequest(mechanicId));
		return new ResponseEntity<ServiceMessage>(msgobj, HttpStatus.OK);
	}
	@PutMapping("/updateMechanic")
	public ResponseEntity<MechanicMessage> UpdateMechanicById(@Valid @RequestBody Mechanic mechanic) throws MechanicNotFoundException {
			mechanicService.addMechanic(mechanic);
			MechanicMessage msgobj=new MechanicMessage();
			msgobj.setResMessage("Returning mechanic list");
			msgobj.setMechanicList(mechanicService.viewMechanic(mechanic.getMechanicId()));
			LOGGER.info("Update Mechanic");
			return new ResponseEntity<MechanicMessage>(msgobj, HttpStatus.OK);
	}
	@GetMapping("/getMechanic/{mechanicId}")
	public ResponseEntity<MechanicMessage> viewMechanic(@PathVariable("mechanicId") int mechanicId) throws RequestNotFoundException{
		LOGGER.info("Viewing Request according to mechanic id");
//		List<Service> viewRequest = mechanicService.viewRequest(mechanicId);
		MechanicMessage msgobj=new MechanicMessage();
		msgobj.setResMessage("Returning mechanic list");
		msgobj.setMechanicList(mechanicService.viewMechanic(mechanicId));
		return new ResponseEntity<MechanicMessage>(msgobj, HttpStatus.OK);
	}

}
