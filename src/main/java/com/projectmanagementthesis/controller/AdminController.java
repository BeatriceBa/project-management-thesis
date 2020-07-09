package com.projectmanagementthesis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.projectmanagementthesis.requests.AddActivityRequest;
import com.projectmanagementthesis.requests.AddProjectRequest;
import com.projectmanagementthesis.requests.GetInfoRequest;
import com.projectmanagementthesis.responses.GetActivitiesResponse;
import com.projectmanagementthesis.responses.GetProjectResponse;
import com.projectmanagementthesis.responses.MessageResponse;
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

	
}
