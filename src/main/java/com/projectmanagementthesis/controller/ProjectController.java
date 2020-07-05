package com.projectmanagementthesis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectmanagementthesis.model.*;
import com.projectmanagementthesis.repositories.*;

@Controller
@RequestMapping("/admin")
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@PostMapping("/addProject")
	public @ResponseBody String addNewProject (
			@RequestParam(name = "name") String name,
			@RequestParam(name = "budget") float budget) {
		
//		System.out.println("Name is: " + name + " and budget is " + budget);
//		Project project = new Project(name, budget);
//		projectRepository.save(project);
		return "index";
	}
}
