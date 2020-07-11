package com.projectmanagementthesis.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UAKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "activity_id")
	private Integer activityId;

}
