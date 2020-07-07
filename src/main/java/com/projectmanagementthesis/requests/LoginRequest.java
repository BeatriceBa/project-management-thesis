package com.projectmanagementthesis.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	private String mail;
	private String password;
}
