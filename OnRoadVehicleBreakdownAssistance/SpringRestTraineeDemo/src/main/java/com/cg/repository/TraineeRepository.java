package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.Trainee;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee,Integer>{
	

}
