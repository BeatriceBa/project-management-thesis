package com.projectmanagementthesis.repositories;

import org.springframework.data.repository.CrudRepository;

import com.projectmanagementthesis.model.Task;


public interface TaskRepository extends CrudRepository<Task,Integer> {

}