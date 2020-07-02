package com.projectmanagementthesis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.projectmanagementthesis.model.*;
import com.projectmanagementthesis.repositories.*;
import com.projectmanagementthesis.websecurity.CredentialValidator;
import com.projectmanagementthesis.websecurity.SecurityService;
import com.projectmanagementthesis.websecurity.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private CredentialValidator credentialValidator;

	@GetMapping("/registration")
	public String registration (Model model) {
		model.addAttribute("userForm", new User());
		return "registration";
	}
	
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		credentialValidator.validate(userForm, bindingResult);
		
		if(bindingResult.hasErrors()) return "registration";
		
		userService.save(userForm);
		securityService.autoLogin(userForm.getMail(), userForm.getRegistrationPasswordConfirmation());
		return "redirect:/welcome";
	}
	
	@GetMapping("/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your mail and password is invalid.");
		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		return "login";
	}
	
	@GetMapping({"/","/welcome"})
	public String welcome(Model model) {
		return "welcome";
	}
	
}
