package com.projectmanagementthesis.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
		/*
		 * public User(@NotBlank String name, 
				@NotBlank String surname,
				@Email(message = "Enter a valid email address.") @NotBlank String mail,
				@Size(min = 5, max = 30, message = "Password must be 5-30 characters long.") String password) {
		 */
//		User user = new User("name","surname","email@test.com","password");
//		User addedUser = ps.addNewUser(user);
//		
//		Project project_1 = new Project("Project_1", 10000, LocalDate.now(), LocalDate.now().plusYears(2));
//		ps.addNewProject(project_1);
//		//	public Activity(String name, float budget, LocalDate beginning, LocalDate end) {
//		Activity activity = new Activity("name",1244,project_1,LocalDate.now(), LocalDate.now().plusYears(2));
//		Activity addedActivity = ps.addNewActivity(activity);
//		
//		UserActivityKey key = new UserActivityKey(addedUser.getId(),addedActivity.getId());
//		UserActivityHour comp = new UserActivityHour(key,addedUser,addedActivity,0);
//		ps.addUserActivityHour(comp);
		
		System.out.println(ps.getUsersAssociated(1));
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