package com.cg.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.model.Trainee;
import com.cg.repository.TraineeRepository;

@Transactional
@Service
public class TraineeServiceImpl implements TraineeService{
	
	@Autowired
	TraineeRepository traineeRepo;
	
	@Override
	public Trainee addTrainee(Trainee trainee) {
		return traineeRepo.save(trainee);
	}

}
