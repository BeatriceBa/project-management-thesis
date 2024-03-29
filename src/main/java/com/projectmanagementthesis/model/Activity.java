package com.projectmanagementthesis.model;

import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private float budget;
	
	private LocalDate beginning;
	
	private LocalDate end;
	
	private float currentBudget = 0;
	
	//---ACTIVITY - TASK---
	@OneToMany(
			mappedBy = "activity",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL 
			)
	@JsonIgnore
	private List<Task> tasks;
	//---
	
	//---ACTIVITY - PROJECT ---
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
	
	@OneToMany(mappedBy = "activity")
	@JsonIgnore
	private List<UserActivityHour> userActivityHour;
	
	public Activity(String name, float budget, LocalDate beginning, LocalDate end) {
		this.name = name;
		this.budget = budget;
		this.beginning = beginning;
		this.end = end;
		this.userActivityHour = new LinkedList<UserActivityHour>();
	}
	
	public Activity(String name, float budget, Project project, LocalDate beginning, LocalDate end) {
		this.name = name;
		this.budget = budget;
		this.project = project;
		this.beginning = beginning;
		this.end = end;
		this.userActivityHour = new LinkedList<UserActivityHour>();
	}

	public Activity(Integer id, String name, float budget, LocalDate beginning, LocalDate end,
			Project project) {
		this.id = id;
		this.name = name;
		this.budget = budget;
		this.beginning = beginning;
		this.end = end;
		this.project = project;
		this.userActivityHour = new LinkedList<UserActivityHour>();
	}
	
	

}