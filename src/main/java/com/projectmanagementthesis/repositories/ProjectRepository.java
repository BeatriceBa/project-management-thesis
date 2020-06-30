package com.projectmanagementthesis.repositories;

import org.springframework.data.repository.CrudRepository;

import com.projectmanagementthesis.model.Project;

public interface ProjectRepository extends CrudRepository<Project,Integer> {

}
