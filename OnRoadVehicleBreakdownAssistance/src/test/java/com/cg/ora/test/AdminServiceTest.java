package com.cg.ora.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
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
import com.cg.ora.model.UserModel;
import com.cg.ora.repository.FeedbackRepository;
import com.cg.ora.repository.MechanicRepository;
import com.cg.ora.repository.ServiceRepository;
import com.cg.ora.repository.UserRepository;
import com.cg.ora.service.AdminServiceImpl;
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
class AdminServiceTest {

	@MockBean
	FeedbackRepository feedbackRepository;
	@MockBean
	MechanicRepository mechanicRepository;
	@MockBean
	UserRepository userRepository;
	@MockBean
	ServiceRepository serviceRepository;
	@InjectMocks
	AdminServiceImpl adminService;
	
	@Autowired
	FeedbackRepository feedbackRepo;
	
	@Test
	public void viewUser() {
		BigInteger number1=new BigInteger("9860754321");
		BigInteger number2=new BigInteger("4321657890");

		UserModel user1=new UserModel();
		user1.setUserName("rakesh");
		user1.setUserPassword("rakesh123");
		user1.setUserEmailId("rakesh123@gmail.com");
		user1.setUserPhoneNumber(number1);
		
		UserModel user2=new UserModel();
		user2.setUserName("laxman");
		user2.setUserPassword("laxman123");
		user2.setUserEmailId("laxman123@gmail.com");
		user2.setUserPhoneNumber(number2);
		
		List<UserModel> userlist=new ArrayList<>();
		userlist.add(user1);
		userlist.add(user2);
		Mockito.when(userRepository.findAll()).thenReturn(userlist);
		assertThat(adminService.viewUserDetails()).isEqualTo(userlist);

	}

	@Test
	public void viewFeedback(){
		
		Mechanic mechanic1=new Mechanic();
		mechanic1.setMechanicName("suresh");
		mechanic1.setMechanicPhoneNumber(BigInteger.valueOf(1236158290));
		mechanic1.setMechanicEmailId("suresh123@gmail.com");
		mechanic1.setMechanicPassword("suresh123");
		mechanic1.setLocation("Mumbai");
		mechanic1.setMechanicServiceType("repair");
		Feedback feedback1=new Feedback();
		feedback1.setUserId(1);
		feedback1.setFeedback("Good mechanic");
		feedback1.setRatings(4);
		
		Feedback feedback2=new Feedback();
		feedback2.setUserId(2);
		feedback2.setFeedback("bad mechanic");
		feedback2.setRatings(2);
		
		List<Feedback> feedbacklist=new ArrayList<>();
		feedbacklist.add(feedback1);
		feedbacklist.add(feedback2);
		Mockito.when(feedbackRepository.findAll()).thenReturn(feedbacklist);
		//assertThat(adminService.viewFeedback(mechanic1)).isEqualTo(feedbacklist);

		
		
	}

	@Test
	public void viewMechanic() {
		Mechanic mechanic1=new Mechanic();
		mechanic1.setMechanicName("suresh");
		mechanic1.setMechanicPhoneNumber(BigInteger.valueOf(1236158290));
		mechanic1.setMechanicEmailId("suresh123@gmail.com");
		mechanic1.setMechanicPassword("suresh123");
		mechanic1.setLocation("Mumbai");
		mechanic1.setMechanicServiceType("repair");
		
		Mechanic mechanic2=new Mechanic();
		mechanic2.setMechanicName("ramesh");
		mechanic2.setMechanicPhoneNumber(BigInteger.valueOf(1236158290));
		mechanic2.setMechanicEmailId("ramesh123@gmail.com");
		mechanic2.setMechanicPassword("ramesh123");
		mechanic2.setLocation("kolkata");
		mechanic2.setMechanicServiceType("repair");
	
		List<Mechanic> mechanicList=new ArrayList<>();
		mechanicList.add(mechanic1);
		mechanicList.add(mechanic2);
		Mockito.when(mechanicRepository.findAll()).thenReturn(mechanicList);
		assertThat(adminService.viewMechanicDetails()).isEqualTo(mechanicList);
	}
	
	
	@Test
	public void adminLogin()
	{
		String username="Admin";
		String password="Admin123";
		
		assertTrue(adminService.loginAdmin(username, password));
		
		String username1="Admin";
		String password1="Admin12345";
		
		assertFalse(adminService.loginAdmin(username1, password1));
	}

}




