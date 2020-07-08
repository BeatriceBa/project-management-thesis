package com.projectmanagementthesis.responses;
import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	
	private String token;
	private String type = "Bearer";
	private Long id;
	private String name;
	private String surname;
	private String email;
	private List<String> roles;
	
	public JwtResponse(String accessToken, Long id, String name, String surname, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.roles = roles;
	}

}
