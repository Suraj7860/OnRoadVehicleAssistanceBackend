package com.cg.ora.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import com.cg.ora.model.Service;
import com.cg.ora.repository.ServiceRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ServiceRepositoryTest {
	
	@Autowired
	private ServiceRepository serviceRepo;
    
	
	@Before
	public Service getService() {
		Service service=new Service();
		service.setServiceType("Diesel engine repair");
		service.setMechanicId(1);
		service.setUserId(1);
		service.setLocation("Mumbai");
		
		return service;
		
	}

	
	// Testing the add service functionality

	@Test
	@Rollback(false)
	@Order(1)
	public void addService()
	{
		Service service=getService();
		Service addService=serviceRepo.save(service);
		Service getService= serviceRepo.getOne(addService.getServiceId());
		assertThat(addService).isEqualTo(getService);
	}
	
	//testing view service by admin
	@Test
	@Order(2)
	public void viewService(){
		List<Service> serviceList=serviceRepo.findAll();
		assertThat(serviceList).size().isGreaterThan(0);
	}
	

}
