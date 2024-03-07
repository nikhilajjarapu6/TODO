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

import com.todo.dto.LoginUser;
import com.todo.dto.User;
import com.todo.services.UserServices;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("app")

public class UserController {
	@Autowired 
	UserServices services; 
	
	//methods for calling initial pages
	//Basic URL for every method is /app/URL
	
	@GetMapping("/TodoHome") 				    //URL for calling home page
	   public String redirectToIndexs() {
	       return "sweet";                     //calls home page
	}
	
	@GetMapping("/registrationForm")
	public String showRegistrationForm() {
	    return "register"; 						//calls register page
	}
      
	@GetMapping("/findUserById")
    public String findByid() {					//calls find by id user page
    	return "findById";
    }
	
    @GetMapping("/deleteUserForm")
    public String deleteForm() {   				//calls delete user
    	return "delete";
    }
    
    @GetMapping("/updateUser/{userId}")
    public String update(@PathVariable int userId, Model model) {
        User user = services.findUser(userId);
        model.addAttribute("user", user);        //calls update page 
        return "updatepage";
    } 
     
    @GetMapping("/loginUser")
    public String loginusers() {				//calls login page
    	return"loginUser";
    } 
     
   
    
   
  
    //controller corresponding methods 
	  
	@PostMapping("/regesterUser")   
	public User registerUser(@RequestBody User user, Model model) {
		User user2= services.registerUser(user);
		model.addAttribute("users", user2);           //adding user object with a name
		return user2; 							  // it will navigates to the home page called sweet 
	}
	
	
	@GetMapping("/listOfAllUsers")
	public String listOfAll(Model model) {
	    List<User> users = services.listOfAll();
	    model.addAttribute("users", users);
	    return "list";
	}
	

//	@GetMapping("/listOfAllUsers")
//	public List<User> listOfAll(Model model) {
//	    List<User> users = services.listOfAll();
//	    model.addAttribute("users", users);
//	    return users;
//	}
	
	
	@GetMapping("/findUser/{id}")
	public String findUserById(@RequestParam int id, Model model) {
	    User user = services.findUser(id);
	    model.addAttribute("user", user);      //adding user object with user name
	    return "findUser";					   //returns finduser page
	}       
 

	@PutMapping("/updateUser")  
	public String updateUser(@RequestBody User user, Model model) {
		User user2=services.updateUser(user);
		System.out.println(user2.getFname());
		model.addAttribute("updatedUser",user2); 
		return "sweet"; 	 
	}
	
	           
	@DeleteMapping("/removeUser/{id}")
	public String deleteUser(@PathVariable String id) {
		 int userId = Integer.parseInt(id);
		User user=services.deleteUser(userId);
		return "sweet";
	}       

	@GetMapping("/userTodoLogin")
	public String loginUser(@RequestParam String email, @RequestParam String password, HttpServletRequest request, Model model) {
	    LoginUser loginUser = new LoginUser();
	    loginUser.setEmail(email);
	    loginUser.setPassword(password);
	    User user = services.validateUser(loginUser, request);
	    if (user != null) {
	        model.addAttribute("user", user);
	        return "redirect:/todo/userTodos";
	    } else {
	        model.addAttribute("error", "Invalid credentials. Please try again.");
	        return "loginUser";
	    }
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpServletRequest request) {
		HttpSession session=request.getSession();
		if(session!=null) {
			session.invalidate();
		}
		return "loginUser";
	}


}  








//another way for sending views in spring boot application
//package com.todo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import com.todo.dto.LoginUser;
//import com.todo.dto.User;
//import com.todo.services.UserServices;
//
//
//import java.util.List;
//
//@Controller
//@RequestMapping("app")
//public class UserController {
//    @Autowired
//    UserServices services;
//
//    @GetMapping("/")
//    public String redirectToIndex() {
//        return "redirect:index";
//    }
//
//    @PostMapping("/registerUser")
//    public String registerUser(@RequestBody User user) {
//        User user2 = services.registerUser(user);
//        // You can handle the registration logic here
//        return "redirect:/home";
//    }
//
//    @GetMapping("/listOfAllUsers")
//    public String listOfAll(Model model) {
//        List<User> users = services.listOfAll();
//        model.addAttribute("users", users);
//        return "userList"; // Assuming you have a user list HTML/JSP page
//    }
//
//    @GetMapping("/findUserById/{id}")
//    public String findById(@PathVariable int id, Model model) {
//        User user = services.findUser(id);
//        model.addAttribute("user", user);
//        return "userInfo"; // Assuming you have a user info HTML/JSP page
//    }
//
//    @PutMapping("/updateUser")
//    public String updateUser(@RequestBody User user) {
//        services.updateUser(user);
//        // You can handle the update logic here
//        return "redirect:/user/listOfAllUsers";
//    }
//
//    @DeleteMapping("/removeUser/{id}")
//    public String deleteUser(@PathVariable int id) {
//        services.deleteUser(id);
//        // You can handle the deletion logic here
//        return "redirect:/user/listOfAllUsers";
//    }
//
//    @GetMapping("/userTodo/loginUser")
//    public String loginUser(@RequestBody LoginUser loginUser, jakarta.servlet.http.HttpServletRequest request, Model model) {
//        User user = services.validateUser(loginUser, request);
//        model.addAttribute("user", user);
//        return "userHome"; // Assuming you have a user home HTML/JSP page
//    }

//@GetMapping("/registrationForm")
//public RedirectView redirectToIndex() {
//    RedirectView redirectView = new RedirectView();
//    redirectView.setUrl("/register.html");
//    return redirectView;
//}
//}

//@GetMapping("/userTodoLogin")
//public String loginUser(@RequestBody LoginUser loginUser, HttpServletRequest request,Model model) { 
//	User user=services.validateUser(loginUser, request);
//	model.addAttribute("loginUser", user);
//	return "home";
//}

//@PostMapping("/updateUser") 
//public String updateUser(@RequestBody User user, Model model) {
//	User user2=services.updateUser(user);
//	System.out.println(user2.getFname());
//	model.addAttribute("updatedUser",user2); 
//	return "sweet";    
//}

