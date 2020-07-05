package com.projectmanagementthesis.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.projectmanagementthesis.model.*;
import com.projectmanagementthesis.repositories.*;


@Controller
@RequestMapping("/admin")
public class ActivityController {
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/addActivity")
	public @ResponseBody String addNewActivity (
			@RequestParam(name = "budget") float budget,
			@RequestParam(name = "task") List<String> task_list) {

		return "Added";
	}
	
	@GetMapping("/populate")
	public @ResponseBody String populate() {
		Project project_1 = new Project("Project_1", 10000, LocalDate.now(), LocalDate.now().plusYears(2));
		Project project_2 = new Project("Project_2", 20000, LocalDate.now(), LocalDate.now().plusYears(2));
		
		projectRepository.save(project_1);
		projectRepository.save(project_2);
		
		Activity activity_1 = new Activity("Activity_1", 5000, project_1, LocalDate.now(), LocalDate.now().plusYears(1));
		Activity activity_2 = new Activity("Activity_2", 5000, project_1, LocalDate.now().plusYears(1), LocalDate.now().plusYears(2));
		Activity activity_3 = new Activity("Activity_3", 10000, project_2, LocalDate.now(), LocalDate.now().plusYears(1));
		Activity activity_4 = new Activity("Activity_4", 10000, project_2, LocalDate.now().plusYears(1), LocalDate.now().plusYears(2));

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
		
		User user_1 = new User("Name_1", "Surname_1", "email_1@test.test", "password_1");
		User user_2 = new User("Name_2", "Surname_2", "email_2@test.test", "password_2");
		User user_3 = new User("Name_3", "Surname_3", "email_3@test.test", "password_3");
		User user_4 = new User("Name_4", "Surname_4", "email_4@test.test", "password_4");
		User user_5 = new User("Name_5", "Surname_5", "email_5@test.test", "password_5");
		User user_6 = new User("Name_6", "Surname_6", "email_6@test.test", "password_6");
		User user_7 = new User("Name_7", "Surname_7", "email_7@test.test", "password_7");
		User user_8 = new User("Name_8", "Surname_8", "email_8@test.test", "password_8");
		
		userRepository.save(user_1);
		userRepository.save(user_2);
		userRepository.save(user_3);
		userRepository.save(user_4);
		userRepository.save(user_5);
		userRepository.save(user_6);
		userRepository.save(user_7);
		userRepository.save(user_8);
		
		user_1.getActivities().addAll(Arrays.asList(activity_1,activity_2));
		userRepository.save(user_1);

		
		return "check workbench pls";
	}
	
	@GetMapping("/showActivities")
	public @ResponseBody Iterable<Activity> getAllActivities() {
		return activityRepository.findAll();
	}
	
}
