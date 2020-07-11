package com.projectmanagementthesis.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityHour {
	@Override
	public String toString() {
		return "UserActivityHour [key=" + key + ", user=" + user + ", activity=" + activity + ", hours=" + hours + "]";
	}

	@EmbeddedId
	private UAKey key;
	
	@ManyToOne
	@MapsId("user_id")
	@JoinColumn(name= "user_id")
	private User user;
	
	@ManyToOne
	@MapsId("activity_id")
	@JoinColumn(name = "activity_id")
	private Activity activity;
	
	private Integer hours;
	
}
