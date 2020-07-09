package com.projectmanagementthesis.requests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddActivityRequest {
	private String name;
	
	private float budget;
	
	private LocalDate beginning;
	
	private LocalDate end;
	
	private Integer projectId;
}
