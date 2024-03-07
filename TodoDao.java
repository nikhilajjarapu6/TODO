package com.todo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todo.dto.Todo;
import com.todo.dto.User;
import com.todo.repository.TodoRepository;

@Repository
public class TodoDao {
	@Autowired
	TodoRepository repository;
	
	
	
	public Todo saveTodo(Todo todo) {
		return repository.save(todo);
	}
	
	public List<Todo> listAll(){
		return repository.findAll();
	}
	public Todo findById(int id) {
		Optional<Todo> optional=repository.findById(id);
		return optional.get();
	}
	
	public Todo updateTodo(Todo todo) {
		Optional<Todo> optional=repository.findById(todo.getId());
		if(optional!=null) {
			repository.save(todo);
		}
		return optional.get();
	}
	
	public Todo deleteTodo(int id) {
		Optional<Todo> optional=repository.findById(id);
		if(optional!=null) {
			repository.delete(optional.get());
		}
		return optional.get();
	}
	
	public List<Todo> findAllByUserId(int userId) {
	    return repository.findAllByUserId(userId);
	}

}
