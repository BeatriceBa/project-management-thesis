package com.projectmanagementthesis.responses;

import java.util.List;

import com.projectmanagementthesis.model.Activity;
import com.projectmanagementthesis.model.Project;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetActivitiesResponse {
	private Project project;
	
	private List <Activity> activities;
}
