package com.cg.ora.repository;



import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ora.model.UserModel;

//This interface extends JpaRepository having parameters of User class and unique key of type integer
@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer>{

	public UserModel findByUserEmailId(String email);

	public List<UserModel> getByUserEmailId(@Valid String email);

	public List<UserModel> getByUserId(@Valid int userId);
}