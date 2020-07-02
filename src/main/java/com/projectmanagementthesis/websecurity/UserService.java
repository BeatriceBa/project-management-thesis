package com.projectmanagementthesis.websecurity;

import com.projectmanagementthesis.model.User;

public interface UserService {
	void save (User user);
	
	User findByMail(String mail);
}
