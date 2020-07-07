package com.projectmanagementthesis.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
	private String name;

	private String surname;

	private String mail;

	private String password;
}
