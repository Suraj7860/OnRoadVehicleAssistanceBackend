package com.cg.controller;
import com.cg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.service.TraineeService;

@RestController
@RequestMapping("/t")
public class TraineeController {
	@Autowired 
	TraineeService traineeService;
	
	@RequestMapping("/addTrainee")
	public ResponseEntity<Trainee> addTrainee(@RequestBody Trainee trainee) {
		
		Trainee addTrainee = traineeService.addTrainee(trainee);
		if (addTrainee == null) {
			return new ResponseEntity("Trainee not added", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Trainee>(addTrainee, HttpStatus.OK);
	}
	

}
