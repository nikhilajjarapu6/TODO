package com.todo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todo.dto.LoginUser;
import com.todo.dto.User;
import com.todo.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Repository
public class UserDao {
	@Autowired
	UserRepository repository;
	
	public User registerUser(User user) {
		return repository.save(user);
	}
	
	public List<User> ListOfUsers() {
		return repository.findAll();
		
	}
	
	public User findById(int id) {
		Optional<User> optional=repository.findById(id);
		return optional.get();
	}

	public User updateUser(User user) {
	    Optional<User> optional = repository.findById(user.getId());
	    
	    if (optional.isPresent()) {
	        repository.save(user);
	    }
	    
	    return optional.orElse(null);
	}

	public User removeUser(int id) {
		Optional<User> optional=repository.findById(id);
		if(!optional.isEmpty())
		 repository.delete(optional.get());
		return optional.get();
	}
	
	//login method
	public User validateUser(LoginUser loginUser ) {
		User user=repository.validateUser(loginUser.getEmail(),loginUser.getPassword());
		return user;
	}
}
