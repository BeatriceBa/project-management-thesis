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
	private String mail;
	private float pricePerHour;
	private int dailyHours;
	private List<String> roles;
	
	public JwtResponse(String accessToken, Long id, String name, String surname, String mail, 
			float pricePerHour, int dailyHours,  List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.pricePerHour = pricePerHour;
		this.dailyHours = dailyHours;
		this.roles = roles;
	}

}
