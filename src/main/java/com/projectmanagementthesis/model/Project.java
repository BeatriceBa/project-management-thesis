package com.projectmanagementthesis.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	
	private float budget;
	
	private LocalDate beginning;
	
	private LocalDate end;
	
	//---PROJECT - ACTIVITY---
	@OneToMany(
			mappedBy = "project",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL 
			)
	@JsonIgnore
	private List<Activity> activities_list;
	//---
	
	public Project(String name, float budget, LocalDate beginning, LocalDate end) {
		this.name = name;
		this.budget = budget;
		this.beginning = beginning;
		this.end = end;
	}
}

