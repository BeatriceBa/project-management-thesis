package com.projectmanagementthesis.service.confirmRegistration;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectmanagementthesis.model.User;
import com.projectmanagementthesis.model.UserType;
import com.projectmanagementthesis.repositories.UserRepository;
import com.projectmanagementthesis.requests.SignUpRequest;
import com.projectmanagementthesis.service.confirmRegistration.ConfirmationToken;
import com.projectmanagementthesis.service.confirmRegistration.ConfirmationTokenService;
import com.projectmanagementthesis.service.confirmRegistration.EmailSenderService;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ConfirmationTokenService confirmationTokenService;


	public void sendConfirmationMail(String userMail, String token) throws IOException {
		EmailSenderService.send(userMail, token);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final Optional<User> optionalUser = userRepository.findByMail(email);
		return optionalUser.orElseThrow(() -> new UsernameNotFoundException
			(MessageFormat.format("User with email {0} cannot be found.", email)));
	}

	
	public boolean signUpUser(SignUpRequest signUpRequest) throws IOException {
		Optional<User> existingUser = userRepository.findByMail(signUpRequest.getMail());
		if (!existingUser.isPresent()) {
			User user = new User(signUpRequest.getName(), 
					signUpRequest.getSurname(),
					signUpRequest.getPricePerHour(),
					signUpRequest.getMail(),
					signUpRequest.getPassword());
			
			final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			user.setUserRole(UserType.USER);

			userRepository.save(user);
			
			final ConfirmationToken confirmationToken = new ConfirmationToken(user);
			confirmationTokenService.saveConfirmationToken(confirmationToken);
			sendConfirmationMail(user.getMail(), confirmationToken.getConfirmationToken());
			return true;
		}
		return false;
	}
	
	public boolean confirmUser(String token) {
		Optional<ConfirmationToken> confirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);
		if(confirmationToken.isPresent()) {
			final User user = confirmationToken.get().getUser();
			user.setEnabled(true);
			userRepository.save(user);
			confirmationTokenService.deleteConfirmationToken(confirmationToken.get().getId());
			return true;
		}
		return false;
	}
	
	public Iterable<User> getUsers() {
		return userRepository.findAll();
	}
	
	public User deleteById(long id) {
		Optional<User> deleted = userRepository.findById(id);
		if(deleted.isPresent())	{
			userRepository.delete(deleted.get());
			return deleted.get();
		}
		return null;
	}
	
	//Just for faster testing process
	public boolean signUpUserNoConfirmation(SignUpRequest signUpRequest) {
		
		Optional<User> existingUser = userRepository.findByMail(signUpRequest.getMail());
		if (!existingUser.isPresent()) {
			
			User user = new User(signUpRequest.getName(), 
					signUpRequest.getSurname(),
					signUpRequest.getPricePerHour(),
					signUpRequest.getMail(),
					signUpRequest.getPassword());
			
			final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			user.setEnabled(true);
			
			userRepository.save(user);
			return true;
		}
		return false;

	}
	
	//Just for faster testing process
	public boolean signUpAdminNoConfirmation(SignUpRequest signUpRequest) {
		
		Optional<User> existingUser = userRepository.findByMail(signUpRequest.getMail());
		if (!existingUser.isPresent()) {
			
			User user = new User(signUpRequest.getName(), 
					signUpRequest.getSurname(),
					signUpRequest.getPricePerHour(),
					signUpRequest.getMail(),
					signUpRequest.getPassword());
			
			final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			user.setEnabled(true);
			user.setUserRole(UserType.ADMINISTRATOR);

			userRepository.save(user);
			return true;
		}
		return false;

	}

	
}

//https://medium.com/@kamer.dev/spring-boot-user-registration-and-login-43a33ea19745

