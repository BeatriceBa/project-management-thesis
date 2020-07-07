package com.projectmanagementthesis.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagementthesis.model.User;
import com.projectmanagementthesis.service.confirmRegistration.UserService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/dashboard")
	String adminWelcomePage() {
		return "welcome";
	}
	
	@GetMapping("/admin/showUsers")
    public Iterable<User> findAllUsers() {
        List<User> users = new LinkedList<User>();
        userService.getUsers().forEach(users::add);
        return userService.getUsers();
        //return new ResponseEntity<>(users, HttpStatus.OK);
    }
	
	@DeleteMapping("admin/users/{id}")
	public ResponseEntity<Object> deleteCourse(@PathVariable long id) {
		User deleted = userService.deleteById(id);
		if(deleted != null)	return ResponseEntity.noContent().build();
		return ResponseEntity.notFound().build();
	}
}
