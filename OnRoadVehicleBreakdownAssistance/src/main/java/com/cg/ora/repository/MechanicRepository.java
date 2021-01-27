package com.cg.ora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.ora.model.Mechanic;

//This interface extends JpaRepository having parameters of Mechanic class and unique key of type integer

public interface MechanicRepository extends JpaRepository<Mechanic, Integer> {

	public List<Mechanic> getMechanicByLocationIgnoreCase(String location);

	public void deleteByMechanicId(int mechanicId);

	public Mechanic findByMechanicId(int mechanicId);

	public Mechanic findByMechanicEmailId(String email);

	public List<Mechanic> getByMechanicId(int mechanicId);

	public List<Mechanic> getByMechanicEmailId(String email );
	
	
	

}
