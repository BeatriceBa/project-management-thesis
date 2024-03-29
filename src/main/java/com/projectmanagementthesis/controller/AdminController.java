package com.projectmanagementthesis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagementthesis.model.*;
import com.projectmanagementthesis.requests.*;
import com.projectmanagementthesis.responses.*;
import com.projectmanagementthesis.service.project.ProjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping("/addProject")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity<?> addProject(@Valid @RequestBody AddProjectRequest request) {
		projectService.saveProject
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
	
	@GetMapping("/getAvailableUsers")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public List<User> getAvailableUsers() {
		return projectService.getAvailableUsers();
	}
	
	
	@PostMapping("/getActivities")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public List<Activity> getActivities(@Valid @RequestBody ProjectRequest request) {
		List<Activity> activities = projectService.getActivitiesPerProject(request.getProjectId());
		return activities;
	}
	
	@PostMapping("/getProject")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public Project getProject(@Valid @RequestBody ProjectRequest request) {
		Project currentProject = projectService.getSingleProject(request.getProjectId());
		return currentProject;
	}
	
	@PostMapping("/addActivity")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity<?> addActivity(@Valid @RequestBody AddActivityRequest request) {
		Project currentProject = projectService.getSingleProject(request.getProjectId());

		projectService.saveActivity
				(new Activity(request.getName(), request.getBudget(),currentProject, request.getBeginning(), request.getEnd()));
		
		return ResponseEntity.ok(new MessageResponse("Activity added successfully!"));
	}
	
	@PostMapping("/associateUser")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity<?> associateUser(@Valid @RequestBody UserActivityRequest request) {		
		Activity activity = projectService.getSingleActivity(request.getActivityId());
		User user = projectService.getSingleUser(request.getUserId());

		UserActivityHour userActivityHour = new UserActivityHour
				(new UAKey(request.getUserId(),request.getActivityId()), user, activity, 0);

		if(projectService.saveUserActivityHour(userActivityHour) != null)
			return ResponseEntity.ok(new MessageResponse("User associated successfully!"));
		else return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(new MessageResponse("User already associated to this activity"));
	}
	
	@PostMapping("/getUsersAssociated")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public List<User> getUsersAssociated(@Valid @RequestBody SeeUsersAssociatedRequest request) {
		List<User> usersAssociated = projectService.getUsersAssociated(request.getActivityId());
		return usersAssociated;
	}
	
	@PostMapping("/getActivity")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public Activity getActivity(@Valid @RequestBody ActivityRequest request) {
		Activity activity = projectService.getSingleActivity(request.getActivityId());
		
		return activity;
	}
	
	@PostMapping("/getCurrentProjectBudget")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public float getCurrentProjectBudget(@Valid @RequestBody ProjectRequest request) {
		return projectService.getCurrentProjectBudget(request.getProjectId());
	}
	
	@PostMapping("/updateProject")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity<?> updateProject(@Valid @RequestBody UpdateProjectRequest request) {
		projectService.saveProject
				(new Project(request.getProjectId(), request.getName(), request.getBudget(), request.getBeginning(), request.getEnd()));
		
		return ResponseEntity.ok(new MessageResponse("Project updated successfully!"));
	}
	
	@PostMapping("/deleteProject")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity<?> deleteProject(@Valid @RequestBody ProjectRequest request) {
		projectService.deleteProject(request.getProjectId());
		return ResponseEntity.ok(new MessageResponse("Project deleted successfully!"));
	}
	
	@PostMapping("/updateActivity")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity<?> updateActivity(@Valid @RequestBody UpdateActivityRequest request) {
		Project relatedProject = projectService.getSingleProject(request.getProjectId());
		
		projectService.saveActivity
			(new Activity (request.getActivityId(), request.getName(), request.getBudget(), request.getBeginning(), 
					request.getEnd(), relatedProject));
		
		return ResponseEntity.ok(new MessageResponse("Activity updated successfully!"));
	}
	
	@PostMapping("/deleteActivity")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public ResponseEntity<?> deleteActivity(@Valid @RequestBody ActivityRequest request) {
		projectService.deleteActivity(request.getActivityId());
		return ResponseEntity.ok(new MessageResponse("Activity deleted successfully!"));
	}
	
	@PostMapping("/getProjectFromActivity")
	@PreAuthorize("hasAuthority('ADMINISTRATOR')")
	public Integer getProjectFromActivity(@Valid @RequestBody ActivityRequest request) {
		Activity activity = projectService.getSingleActivity(request.getActivityId());
		return activity.getProject().getId();
	}

}
