package com.projectmanagementthesis.requests;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProjectRequest {
	private Integer projectId;
	
	private String name;
	
	private float budget;
	
	private LocalDate beginning;
	
	private LocalDate end;
}
