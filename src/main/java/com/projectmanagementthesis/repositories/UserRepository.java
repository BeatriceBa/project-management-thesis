package com.projectmanagementthesis.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.projectmanagementthesis.model.User;

public interface UserRepository extends CrudRepository<User,Long> {
	Optional<User> findByMail(String mail);
}
