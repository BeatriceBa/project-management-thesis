package com.projectmanagementthesis.repositories;

import org.springframework.data.repository.CrudRepository;

import com.projectmanagementthesis.model.User;

public interface UserRepository extends CrudRepository<User,Integer> {
	User findByMail(String mail);
}
