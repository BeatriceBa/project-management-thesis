package com.projectmanagementthesis.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.projectmanagementthesis.model.User;
import com.projectmanagementthesis.model.UserType;

public interface UserRepository extends CrudRepository<User,Long> {
	Optional<User> findByMail(String mail);
	List<User> findByuserRole(UserType userType);
}
