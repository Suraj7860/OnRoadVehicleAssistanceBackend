package com.cg.ora.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.cg.ora.model.Feedback;
import com.cg.ora.model.Mechanic;

//This interface extends JpaRepository having parameters of Feedback class and unique key of type integer
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer>{

	public boolean findByUserId(int userId);


	public List<Feedback> findByMechanic(Mechanic m);


	public Feedback findByUserIdAndMechanic(int userId, Mechanic m);

    @Query("SELECT SUM(f.ratings) from Feedback f where f.mechanic.mechanicId =:mechanicId")
	public Double sumOfRatings(@Param("mechanicId") int mechanicId);
    
    @Query("SELECT COUNT(f.ratings) from Feedback f where f.mechanic.mechanicId =:mechanicId")
    public Long countOfRatings(@Param("mechanicId") int mechanicId);

	
	
	
}
