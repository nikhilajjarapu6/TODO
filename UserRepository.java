package com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.todo.dto.User;


public interface UserRepository extends JpaRepository<User,Integer>{
	@Query("select a from User a where a.email=:email and a.password=:password")
	public User validateUser(@Param("email") String email,@Param("password") String password);
	
	
}
