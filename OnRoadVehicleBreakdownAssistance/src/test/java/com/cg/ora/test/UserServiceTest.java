package com.cg.ora.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.cg.ora.model.Mechanic;
import com.cg.ora.model.Service;
import com.cg.ora.model.UserModel;
import com.cg.ora.repository.MechanicRepository;
import com.cg.ora.repository.ServiceRepository;
import com.cg.ora.repository.UserRepository;
import com.cg.ora.service.MechanicServiceImpl;
import com.cg.ora.service.UserServiceImpl;
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;
	
	@MockBean
	UserRepository userRepository;
	
	@MockBean
	MechanicRepository mechanicRepository;
	
	@MockBean
	ServiceRepository serviceRepository;
	
	@InjectMocks
	MechanicServiceImpl mechanicService;
	
	
	
	
	@Test
	public void addUser ()
	{
		UserModel user = new UserModel();
		user.setUserName("ramesh");
		user.setUserEmailId("ramesh@gmail.com");
		user.setUserPhoneNumber(BigInteger.valueOf(9089786756l));
		user.setUserPassword("ramesh123");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		assertThat(userService.userRegistration(user)).isEqualTo(user);
		
		
	}
	
	@Test
	public void deleteUserById() {
		UserModel user=new UserModel();
		user.setUserId(40);
		user.setUserName("rakesh");
		user.setUserPassword("rakesh123");
		user.setUserPhoneNumber(BigInteger.valueOf(9089786756l));
		user.setUserEmailId("rakesh123@gmail.com");
		
		Mockito.when(userRepository.getOne(40)).thenReturn(user);
		Mockito.when(userRepository.existsById(user.getUserId())).thenReturn(true);
		assertTrue(userRepository.existsById(user.getUserId()));
		
	}
	
	@Test
	public void updateUser(){
		UserModel user=new UserModel();
		user.setUserId(40);
		user.setUserName("rakesh");
		user.setUserPassword("rakesh123");
		user.setUserPhoneNumber(BigInteger.valueOf(9089786756l));
		user.setUserEmailId("rakesh123@gmail.com");
		Mockito.when(userRepository.getOne(1)).thenReturn(user);
		user.setUserPassword("rakya321");;
		Mockito.when(userRepository.save(user)).thenReturn(user);
		
		assertEquals("rakya321",user.getUserPassword());
	}
	
	@Test
	public void searchMechanicByLocation() {
		Mechanic mechanic=new Mechanic();
		mechanic.setMechanicName("suresh");
		mechanic.setMechanicPhoneNumber(BigInteger.valueOf(1236158290));
		mechanic.setMechanicEmailId("suresh123@gmail.com");
		mechanic.setMechanicPassword("suresh123");
		mechanic.setLocation("Mumbai");
		mechanic.setMechanicServiceType("repair");
		Mockito.when(mechanicRepository.save(mechanic)).thenReturn(mechanic);
		assertEquals("Mumbai",mechanic.getLocation());
	}
	
	
	@Test
	public void addRequest()
	{
		Service service =new Service();
		service.setServiceId(90);
		service.setUserId(8);
		service.setMechanicId(22);
		service.setLocation("Mumbai");
		service.setServiceType("petrol diesel");
		Mockito.when(serviceRepository.getByMechanicId(22)).thenReturn(service);
		Mockito.when(serviceRepository.existsById(service.getMechanicId())).thenReturn(true);
		assertTrue(serviceRepository.existsById(service.getMechanicId()));
	}
	
	
	@Test
	public void userLogin() 
	{
		UserModel user = new UserModel();
		user.setUserName("ramesh");
		user.setUserEmailId("ramesh@gmail.com");
		user.setUserPhoneNumber(BigInteger.valueOf(9089786756l));
		user.setUserPassword("ramesh123");
		assertEquals(user.getUserEmailId(),"ramesh@gmail.com");
		assertEquals(user.getUserPassword(),"ramesh123");
		Mockito.when(userRepository.findByUserEmailId(user.getUserEmailId())).thenReturn(user);
		assertTrue(userService.loginUser(user.getUserEmailId(),user.getUserPassword()));
	}
	
	

}
