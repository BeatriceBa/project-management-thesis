package com.projectmanagementthesis.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagementthesis.model.*;
import com.projectmanagementthesis.service.registration.ConfirmationToken;
import com.projectmanagementthesis.service.registration.ConfirmationTokenService;
import com.projectmanagementthesis.service.registration.UserService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@GetMapping("/sign-in")
	String signIn() {
		return "sign-in";
	}

	@PostMapping("/sign-in")
	String signIpPage(User user) {
		return "redirect:/user-welcome";
	}

	@GetMapping("/sign-up")
	String signUpPage(User user) {
		return "sign-up";
	}

	@PostMapping("/sign-up")
	String signUp(User user) {
		//userService.signUpUser(user);
		userService.signUpAdminNoConfirmation(user);
		return "redirect:/sign-in";
	}

	@GetMapping("/sign-up/confirm")
	String confirmMail(@RequestParam("token") String token) {
		Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);
		optionalConfirmationToken.ifPresent(userService::confirmUser);
		return "redirect:/sign-in";
	}
	
	@GetMapping("/user/welcomepage")
	String userWelcomePage(User user) {
		return "user-welcome";
	}
	
	@GetMapping("/admin/welcomepage")
	String adminWelcomePage(User user) {
		return "admin-welcome";
	}
	
	@GetMapping("/test/showUsers")
    public List<User> findAllUsers() {
        List<User> users = new LinkedList<User>();
        userService.getUsers().forEach(users::add);
        return users;
        //return new ResponseEntity<>(users, HttpStatus.OK);
    }

	
}
