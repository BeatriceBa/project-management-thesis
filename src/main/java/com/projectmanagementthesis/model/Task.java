package com.projectmanagementthesis.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Task {
	//Good practice is to make the child the owning part of the relationship
	//Do i agree? NO. Am i gonna do it? yes.
	
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
	
	public Task() { }

	public Task(String name, Activity activity) {
		this.name = name;
		this.activity = activity;
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

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	
	
	
	
}