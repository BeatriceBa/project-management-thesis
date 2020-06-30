package com.projectmanagementthesis.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.projectmanagementthesis.model.*;
import com.projectmanagementthesis.repositories.*;


@Controller
@RequestMapping(path="/activities")
public class ActivityController {
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ProjectRepository projectRepository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewActivity (
			@RequestParam(name = "budget") float budget,
			@RequestParam(name = "task") List<String> task_list) {

		return "Added";
	}
	
	@GetMapping(path="/populate")
	public @ResponseBody String populate() {
		Project project_1 = new Project("Project_1", 10000);
		Project project_2 = new Project("Project_2", 20000);
		
		projectRepository.save(project_1);
		projectRepository.save(project_2);
		
		Activity activity_1 = new Activity("Activity_1", 5000, project_1);
		Activity activity_2 = new Activity("Activity_2", 5000, project_1);
		Activity activity_3 = new Activity("Activity_3", 10000, project_2);
		Activity activity_4 = new Activity("Activity_4", 10000, project_2);

		activityRepository.save(activity_1);
		activityRepository.save(activity_2);
		activityRepository.save(activity_3);
		activityRepository.save(activity_4);
		
		taskRepository.save(new Task("taskName_1", activity_1));
		taskRepository.save(new Task("taskName_2", activity_1));
		taskRepository.save(new Task("taskName_3", activity_2));
		taskRepository.save(new Task("taskName_4", activity_2));
		taskRepository.save(new Task("taskName_5", activity_3));
		taskRepository.save(new Task("taskName_6", activity_3));
		taskRepository.save(new Task("taskName_7", activity_4));
		taskRepository.save(new Task("taskName_8", activity_4));
		
		return "check workbench pls";
	}
	
	@GetMapping(path="/show")
	public @ResponseBody Iterable<Activity> getAllActivities() {
		return activityRepository.findAll();
	}
	
	@GetMapping("/calendar")
	public String calendar() {
		return "calendar";
	}
	
}
