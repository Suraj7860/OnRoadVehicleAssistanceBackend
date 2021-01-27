package com.cg.ora.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.cg.ora.model.Feedback;
import com.cg.ora.model.Mechanic;
import com.cg.ora.model.Service;
import com.cg.ora.repository.FeedbackRepository;
import com.cg.ora.repository.MechanicRepository;
import com.cg.ora.repository.ServiceRepository;
import com.cg.ora.service.MechanicServiceImpl;
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class MechanicServiceTest {
	@Autowired
	ServiceRepository serviceRepo;
	
	@InjectMocks
	MechanicServiceImpl mechanicService;
	
	@MockBean
	MechanicRepository mechanicRepo;
	
	@MockBean
	FeedbackRepository feedbackRepository;
	
	
	
	
	
	@Test
	public void addMechanic ()
	{
		Mechanic mechanic=new Mechanic();
		mechanic.setMechanicName("suresh");
		mechanic.setMechanicPhoneNumber(BigInteger.valueOf(1236158290));
		mechanic.setMechanicEmailId("suresh123@gmail.com");
		mechanic.setMechanicPassword("suresh123");
		mechanic.setLocation("Mumbai");
		mechanic.setMechanicServiceType("repair");
		Mockito.when(mechanicRepo.save(mechanic)).thenReturn(mechanic);
		assertThat(mechanicService.addMechanic(mechanic)).isEqualTo(mechanic);
		
		
	}

	
	@Test
	public void viewFeedback(){
		Feedback feedback=new Feedback();
		feedback.setUserId(1);
		feedback.setFeedback("Good mechanic");
		feedback.setRatings(4.5f);
		Mockito.when(feedbackRepository.save(feedback)).thenReturn(feedback);
		Mockito.when(feedbackRepository.existsById(feedback.getUserId())).thenReturn(true);
		
	}
	
	@Test
	public void mechanicLogin() 
	{
		Mechanic mechanic=new Mechanic();
		mechanic.setMechanicName("suresh");
		mechanic.setMechanicPhoneNumber(BigInteger.valueOf(1236158290));
		mechanic.setMechanicEmailId("suresh123@gmail.com");
		mechanic.setMechanicPassword("suresh123");
		mechanic.setLocation("Mumbai");
		mechanic.setMechanicServiceType("repair");
		
		assertEquals(mechanic.getMechanicEmailId(),"suresh123@gmail.com");
		assertEquals(mechanic.getMechanicPassword(),"suresh123");
		Mockito.when(mechanicRepo.findByMechanicEmailId(mechanic.getMechanicEmailId())).thenReturn(mechanic);
		assertTrue(mechanicService.loginMechanic(mechanic.getMechanicEmailId(),mechanic.getMechanicPassword()));
	}
	
	
}
