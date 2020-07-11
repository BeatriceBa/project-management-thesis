package com.projectmanagementthesis.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.projectmanagementthesis.model.*;

public interface UserActivityHourRepository extends CrudRepository<UserActivityHour,UAKey> {
	public List<UserActivityHour> findByActivity(Activity activity);
	public List<UserActivityHour> findByUser(User user);
}
