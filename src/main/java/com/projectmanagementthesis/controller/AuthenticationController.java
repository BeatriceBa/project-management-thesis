package com.projectmanagementthesis.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagementthesis.model.User;
import com.projectmanagementthesis.requests.*;
import com.projectmanagementthesis.responses.JwtResponse;
import com.projectmanagementthesis.responses.MessageResponse;
import com.projectmanagementthesis.security.jwt.JwtUtils;
import com.projectmanagementthesis.service.confirmRegistration.UserService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getMail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		User userDetails = (User) authentication.getPrincipal();
		System.out.println(userDetails.getAuthorities());
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getName(), userDetails.getSurname(), userDetails.getMail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (!userService.signUpUser(signUpRequest)) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Mail is already taken!"));
		}

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	@PostMapping("/confirmAccount")
	public ResponseEntity<?> confirmUser(@Valid @RequestBody ConfirmAccountRequest request) {
		if (!userService.confirmUser(request.getToken())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: no confirmation token for this user"));
		}

		return ResponseEntity.ok(new MessageResponse("User confirmed successfully! You can now login"));
	}
}
