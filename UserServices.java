package com.todo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.dao.UserDao;
import com.todo.dto.LoginUser;
import com.todo.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServices {
	@Autowired
	UserDao dao;
	
	public User registerUser(User user) {
		return dao.registerUser(user);
	}
	
	public List<User> listOfAll(){
		return dao.ListOfUsers();
	}
	
	public User findUser(int id) {
		return dao.findById(id);
	}
	public User updateUser(User user) {
		return dao.updateUser(user);
	}
	
	public User deleteUser(int id) {
		return dao.removeUser(id);
	}

	public User updateUsers(int id,User user) {
		User user2=dao.findById(id);
		if(user2!=null) {
			dao.updateUser(user2);
			return user2;
		}
		else
			return null;
	}
	public User validateUser(LoginUser loginUser, HttpServletRequest request) {
	    HttpSession session = request.getSession(); 
	        User user = dao.validateUser(loginUser);
	        if (user != null) {
	            session.setAttribute("userSession", user);
	        } else {
	            
	            session.removeAttribute("userSession");
	        }
	        return user;
	}

	
	
}
