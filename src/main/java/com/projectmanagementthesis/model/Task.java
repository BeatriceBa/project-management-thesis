package com.projectmanagementthesis.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Task {
	//Good practice is to make the child the owning part of the relationship (relationships one to many)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	//---ACTIVITY - TASK---
	@ManyToOne(
			fetch = FetchType.LAZY,
			optional = false
			)
	@JoinColumn(
			name = "activity_id",
			nullable = false
			)
	@JsonIgnore
	private Activity activity;
	//---

	public Task(String name, Activity activity) {
		this.name = name;
		this.activity = activity;
	}

}