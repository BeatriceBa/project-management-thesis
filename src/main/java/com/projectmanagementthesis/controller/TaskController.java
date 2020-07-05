package com.projectmanagementthesis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectmanagementthesis.model.*;
import com.projectmanagementthesis.repositories.*;


@Controller
@RequestMapping("/admin")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping(path="/addTask")
	public @ResponseBody String addNewTask (
			@RequestParam(name = "name") String name,
			@RequestParam(name = "activity") Activity activity) {
		
		Task task = new Task(name, activity);
		taskRepository.save(task);
		return "Added";
	}
}