package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.dto.Todo;
import com.todo.dto.User;
import com.todo.services.TodoServices;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
@RequestMapping("todo")
public class TodoController {
	@Autowired
	TodoServices services;
	
	@PostMapping("/savetodo")
	public Todo saveTodo(@RequestBody Todo todo, HttpSession session) {
		return services.saveTodo(todo,session);
	}
	
	@GetMapping("/listOfAllTodos")
	public List<Todo> listAll() {
		return services.listAll();
	} 
	
	@GetMapping("/findTodoById/{id}")
	public Todo findById(@PathVariable int id) {
		return services.findById(id);
	}
	
	@PutMapping("/updateTodos")
	public Todo updateTodo(@RequestBody Todo todo, HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("userSession");
	    todo.setUser(user);
		return services.updateTodo(todo);
	}
	
//	@DeleteMapping("/removeTodo/{id}")
//	public Todo deeteTodo(@PathVariable int id) {
//		return services.deleteTodo(id);
//	}
	
	
	
	
	@GetMapping("/userTodos")
	public String listOfTodos(HttpSession session, Model model){
		if(session!=null) {
		User user=(User)session.getAttribute("userSession");
		System.out.println(user);
		if(user!=null) {
			List<Todo> todos=services.getAllUserTodo(user.getId());
			model.addAttribute("todoList", todos);
			return "userTodo";
		    }
		}  
		 
		return "sweet";   
		 
	}
	
	@GetMapping("/userTodoList")
	public String userTodoList() {
		return "userTodo";
	}
	
	
	@GetMapping("/addtodo")
	public String todoRegisterPage() {
		return "saveTodo";
	}
	
	@GetMapping("/editTodoById/{id}")
	public String editById(@PathVariable int id, Model model) {
		Todo todo= services.findById(id);
		if(todo!=null) {
			model.addAttribute("todo",todo);
		}
		return "updateTodo";
	}
	
	@GetMapping("/removeTodo/{id}")
	public String deeteTodo(@PathVariable int id) {
		Todo todo= services.deleteTodo(id);
		return "redirect:/todo/userTodos";
	}
	
	
	
}  
