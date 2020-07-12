package com.projectmanagementthesis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagementthesis.model.*;
import com.projectmanagementthesis.requests.ActivityRequest;
import com.projectmanagementthesis.requests.ProjectRequest;
import com.projectmanagementthesis.requests.SeeActivitiesAssociatedRequest;
import com.projectmanagementthesis.requests.UserActivityHourRequest;
import com.projectmanagementthesis.requests.UserActivityRequest;
import com.projectmanagementthesis.responses.MessageResponse;
import com.projectmanagementthesis.service.project.ProjectService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private ProjectService projectService;
	
	@PostMapping("/getMyActivities")
	@PreAuthorize("hasAuthority('USER')")
	public List<Activity> getMyActivities(@Valid @RequestBody SeeActivitiesAssociatedRequest request) {
		List<Activity> activitiesAssociated = projectService.getActivitiesAssociated(request.getUserId());
		return activitiesAssociated;
	}
	
	@PostMapping("/seeProjectInfo")
	@PreAuthorize("hasAuthority('USER')")
	public Project seeProjectInfo(@Valid @RequestBody ActivityRequest request) {
		Activity activity = projectService.getSingleActivity(request.getActivityId());
		Project project = projectService.getSingleProject(activity.getProject().getId());
		return project;
	}
	
	@PostMapping("/seeActivities")
	@PreAuthorize("hasAuthority('USER')")
	public List<Activity> seeActivities(@Valid @RequestBody ProjectRequest request) {
		List<Activity> activities = projectService.getActivitiesPerProject(request.getProjectId());
		return activities;
	}
	
	@PostMapping("/registerHours")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<?> registerHours(@Valid @RequestBody UserActivityHourRequest request) {
		UserActivityHour userActivityHour = projectService.getUserActivityHour(request.getUserId(), request.getActivityId());
		if(userActivityHour != null && projectService.RegisterHours(userActivityHour, request.getHours())) {
			return ResponseEntity.ok(new MessageResponse("Hours registered correctly!"));
		}
		return ResponseEntity.badRequest().body(new MessageResponse("Oops something went wrong\n "
				+ "(Maybe you want to work overtime?)\n"
				+ "Contact the Admin for further informations"));
	}
}
