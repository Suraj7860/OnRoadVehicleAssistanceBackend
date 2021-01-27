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

import com.cg.ora.model.Feedback;
import com.cg.ora.model.Mechanic;
import com.cg.ora.repository.FeedbackRepository;
import com.cg.ora.repository.MechanicRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MechanicRepositoryTest {

	
	@Autowired
	private MechanicRepository mechanicRepository;
	
	@Autowired
	FeedbackRepository feedbackRepository;

	// creating a mechanic object

	@Before
	public Mechanic getMechanic() {
		Mechanic mechanic = new Mechanic();
		mechanic.setMechanicName("rushikesh");
		mechanic.setMechanicPhoneNumber(BigInteger.valueOf(1236158290));
		mechanic.setMechanicEmailId("sdf@gmail.com");
		mechanic.setMechanicPassword("sdfsd0");
		mechanic.setLocation("Mumbai");
		mechanic.setMechanicServiceType("repair");
		return mechanic;
	}

	// Testing the add mechanic functionality
	@Test
	@Rollback(false)
	@Order(1)
	public void addMechanic() {
		Mechanic mechanic = getMechanic();

		Mechanic addMechanic = mechanicRepository.save(mechanic);
		Mechanic getMechanic = mechanicRepository.getOne(addMechanic.getMechanicId());
		assertThat(getMechanic).isEqualTo(addMechanic);

	}

	// Viewing the mechanic
	@Test
	@Rollback(false)
	@Order(2)
	public void viewMechanic() {
		List<Mechanic> mechanicList = mechanicRepository.findAll();
		assertThat(mechanicList).size().isGreaterThan(0);

	}
	
	@Test
	@Rollback(false)
	@Order(3)
	public void viewFeedback(){
		List<Feedback> feedbackList=feedbackRepository.findAll();
		assertThat(feedbackList).size().isGreaterThan(0);
	}

}
