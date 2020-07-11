package com.projectmanagementthesis.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.projectmanagementthesis.model.*;

public interface UserActivityHourRepository extends CrudRepository<UserActivityHour,UAKey> {
	List<UserActivityHour> findByActivity(Activity activity);
}
