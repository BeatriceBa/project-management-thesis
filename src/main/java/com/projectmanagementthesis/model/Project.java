package com.projectmanagementthesis.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private float budget;
	
	//---PROJECT - ACTIVITY---
	@OneToMany(
			mappedBy = "project",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL 
			)
	@JsonIgnore
	private List<Activity> activities_list;
	//---
	
	public Project() { }
	
	public Project(String name, float budget) {
		this.name = name;
		this.budget = budget;
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

	public List<Activity> getActivities_list() {
		return activities_list;
	}

	public void setActivities_list(List<Activity> activities_list) {
		this.activities_list = activities_list;
	}
	
	
}

