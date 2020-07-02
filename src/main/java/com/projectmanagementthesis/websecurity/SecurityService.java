package com.projectmanagementthesis.websecurity;

public interface SecurityService {
	String findLoggedInMail();
	
	void autoLogin(String mail, String password);
}
