package com.cg.ora.repository;
import com.cg.ora.model.Service;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//This interface extends JpaRepository having parameters of Service class and unique key of type integer
@Repository
public interface ServiceRepository extends JpaRepository<Service,Integer>{

	public List<Service> findByMechanicId(int mecId);
	
	public Service getByUserId(int userId);
	
	public Service getByMechanicId(int mechanicId);

	public Service findByUserIdAndMechanicId(int mechanicId, int userId);
}
