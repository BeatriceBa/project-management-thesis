package com.projectmanagementthesis.model;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private float budget;
	
	//---ACTIVITY - TASK---
	@OneToMany(
			mappedBy = "activity",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL 
			)
	@JsonIgnore
	private List<Task> tasks;
	//---
	
	//---ACTIVITY - PROJECT---
	@ManyToOne(
			fetch = FetchType.LAZY,
			optional = false
			)
	@JoinColumn(
			name = "project_id",
			nullable = false
			)
	@JsonIgnore
	private Project project;
	//---
	
	public Activity() { }

	public Activity(String name, float budget, Project project) {
		this.name = name;
		this.budget = budget;
		this.project = project;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
}