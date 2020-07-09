package com.projectmanagementthesis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagementthesis.model.*;
import com.projectmanagementthesis.service.addProject.ProjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	ProjectService ps; 
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/test")
	public List<Activity> getActivities() {
		return ps.getActivitiesPerProject(1);
	}
	
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String userAccess() {
		System.out.println("wewe");
		return "User Content.";
	}


	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public String adminAccess() {
		return "Admin Board.";
	}

}