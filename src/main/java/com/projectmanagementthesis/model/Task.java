package com.projectmanagementthesis.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
	//Good practice is to make the child the owning part of the relationship (relationships one to many)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	//---ACTIVITY - TASK - OWNING SIDE---
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