package com.projectmanagementthesis.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String surname;

	@Email(message = "Enter a valid email address.") 
	@NotBlank
	private String mail;

	@Size(min = 5, max = 30, message = "Password must be 5-30 characters long.")
	private String password;

	@Builder.Default
	private UserType userRole = UserType.USER;

	@Builder.Default
	private Boolean locked = false;

	@Builder.Default
	private Boolean enabled = false;
	
	@ManyToMany(mappedBy = "user")
	List<Activity> activities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole.name());
		return Collections.singletonList(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return mail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", mail=" + mail + ", password="
				+ password + ", userRole=" + userRole + ", locked=" + locked + ", enabled=" + enabled + "]";
	}

	
}
