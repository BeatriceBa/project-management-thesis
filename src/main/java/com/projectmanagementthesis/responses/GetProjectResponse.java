package com.projectmanagementthesis.responses;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetProjectResponse {

	private Integer id;
	
	private String name;
	
	private float budget;
	
	private LocalDate beginning;
	
	private LocalDate end;

}
