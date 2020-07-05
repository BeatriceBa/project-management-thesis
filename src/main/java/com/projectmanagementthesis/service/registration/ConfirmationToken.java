package com.projectmanagementthesis.service.registration;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.*;

import com.projectmanagementthesis.model.User;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ConfirmationToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String confirmationToken;
	
	private LocalDate creationDate;
	
	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	
	
	public ConfirmationToken(User user) {
		this.user = user;
		this.creationDate = LocalDate.now();
		this.confirmationToken = UUID.randomUUID().toString();
	}

	//Expires the next day
	public LocalDate expirationDate() {
		return creationDate.plusDays(1);
	}
}
