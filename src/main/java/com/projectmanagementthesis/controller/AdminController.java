package com.projectmanagementthesis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagementthesis.model.*;
import com.projectmanagementthesis.repositories.ActivityRepository;
import com.projectmanagementthesis.requests.*;
import com.projectmanagementthesis.responses.*;
import com.projectmanagementthesis.service.addProject.ProjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping("/addProject")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity<?> addProject(@Valid @RequestBody AddProjectRequest request) {
		projectService.addNewProject
				(new Project(request.getName(), request.getBudget(), request.getBeginning(), request.getEnd()));
		
		return ResponseEntity.ok(new MessageResponse("Project added successfully!"));
	}
	
	@GetMapping("/getProjects")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public List<Project> getProjects() {
		return projectService.getProjects();
	}

	@GetMapping("/getUsers")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public List<User> getUsers() {
		return projectService.getUsers();
	}
	
	@PostMapping("/getActivities")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public List<Activity> getActivities(@Valid @RequestBody GetInfoRequest request) {
		List<Activity> activities = projectService.getActivitiesPerProject(request.getProjectId());
		return activities;
	}
	
	@PostMapping("/getProject")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public Project getProject(@Valid @RequestBody GetInfoRequest request) {
		Project currentProject = projectService.getSingleProject(request.getProjectId());
		return currentProject;
	}
	
	@PostMapping("/addActivity")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity<?> addActivity(@Valid @RequestBody AddActivityRequest request) {
		Project currentProject = projectService.getSingleProject(request.getProjectId());

		projectService.addNewActivity
				(new Activity(request.getName(), request.getBudget(),currentProject, request.getBeginning(), request.getEnd()));
		
		return ResponseEntity.ok(new MessageResponse("Activity added successfully!"));
	}
	
	@PostMapping("/associateUser")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity<?> associateUser(@Valid @RequestBody AssociateUserToActivityRequest request) {
		System.out.println("request user " + request.getUserId() + " request activity " + request.getActivityId());
		
		Activity activity = projectService.getSingleActivity(request.getActivityId());
		User user = projectService.getSingleUser(request.getUserId());
		
		System.out.println("activity_cont " + activity);
		System.out.println("user_cont " + user);

		UserActivityHour userActivityHour = new UserActivityHour
				(new UAKey(request.getUserId(),request.getActivityId()), user, activity, 0);
		System.out.println(userActivityHour);
		if(projectService.addUserActivityHour(userActivityHour) != null)
			return ResponseEntity.ok(new MessageResponse("User associated successfully!"));
		else return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("User already associated to this activity");
	}
	
	@PostMapping("/getUsersAssociated")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public List<User> getUsersAssociated(@Valid @RequestBody SeeUsersAssociatedRequest request) {
		List<User> usersAssociated = projectService.getUsersAssociated(request.getActivityId());
		return usersAssociated;
	}
	
	@PostMapping("/getActivity")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public Activity getActivity(@Valid @RequestBody GetActivityRequest request) {
		Activity activity = projectService.getSingleActivity(request.getActivityId());
		
		return activity;
	}
	

	
}
