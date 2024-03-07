package com.todo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.dao.TodoDao;
import com.todo.dto.Todo;
import com.todo.dto.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class TodoServices {
	@Autowired
	TodoDao dao;
	
	public Todo saveTodo(Todo todo, HttpSession session) {
		if(todo!=null && session!=null) {
			User user =(User) session.getAttribute("userSession");
			todo.setUser(user);
			Todo todo2=dao.saveTodo(todo);
			if(todo2!=null) {
				session.setAttribute("todos", todo2);
				return todo2;
			}
		}
		
		return null;
	}
		
	public List<Todo> listAll(){
		return dao.listAll();
	}
	
	public Todo findById(int id) {
		return dao.findById(id);
	}
	
	public Todo deleteTodo(int id) {
		return dao.deleteTodo(id);
	}
	public Todo updateTodo(Todo todo) {
		return dao.updateTodo(todo);
	}
	
	//to display all todos of specific user
	
	public List<Todo> getAllUserTodo(int id){
		return dao.findAllByUserId(id);
	}
}
