package com.cg.ora.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
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
import com.cg.ora.model.UserModel;
import com.cg.ora.repository.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	
	
    //Creating a user object
	
	@Before
	public UserModel getUser() {
		UserModel user = new UserModel();
		user.setUserName("ashwini");
		user.setUserEmailId("a@gmail.com");
		user.setUserPhoneNumber(BigInteger.valueOf(9089786756l));
		user.setUserPassword("asdfss");
		return user;
	}
	// Testing the add User functionality

	@Test
	@Rollback(false)
	@Order(1)
	public void addUser() {
		UserModel user = getUser();
		UserModel addUser = userRepository.save(user);
		UserModel getUser = userRepository.getOne(addUser.getUserId());
		assertThat(getUser).isEqualTo(addUser);
	}
   
	
	//Viewing the user
	@Test
	@Rollback(false)
	@Order(2)
	public void viewUser() {
		List<UserModel> userList = userRepository.findAll();
		assertThat(userList).size().isGreaterThan(0);

	}

	
}
