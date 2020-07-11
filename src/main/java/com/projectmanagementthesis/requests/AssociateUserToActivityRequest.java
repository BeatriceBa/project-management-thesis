package com.projectmanagementthesis.requests;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociateUserToActivityRequest {
	private long userId;
	
	private Integer activityId;
}
