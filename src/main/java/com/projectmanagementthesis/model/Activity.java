package com.projectmanagementthesis.model;

import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

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
	
	//---ACTIVITY - USER---
	@ManyToMany
	@JoinTable(
			name = "activity_user",
			joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List <User> teachers;
	//---

	
	public Activity(String name, float budget, Project project, LocalDate beginning, LocalDate end) {
		this.name = name;
		this.budget = budget;
		this.project = project;
		this.beginning = beginning;
		this.end = end;
	}
	
}