package com.projectmanagementthesis.model;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private String mail;
	
	private String password;
	
	@Transient
	private String registrationPasswordConfirmation;
	

	private String name;
	private String surname; 
	
	private UserType userType;
	
	public User() { }

	public String getRegistrationPasswordConfirmation() {
		return registrationPasswordConfirmation;
	}
	
	public void setRegistrationPasswordConfirmation(String registrationPasswordConfirmation) {
		this.registrationPasswordConfirmation = registrationPasswordConfirmation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	
	
}
