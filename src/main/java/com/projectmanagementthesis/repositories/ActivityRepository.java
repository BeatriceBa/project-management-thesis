package com.projectmanagementthesis.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.projectmanagementthesis.model.Activity;

public interface ActivityRepository extends CrudRepository<Activity,Integer> {
	List<Activity> findByProjectId(int id);
}

