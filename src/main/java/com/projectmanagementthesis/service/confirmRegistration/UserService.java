package com.projectmanagementthesis.service.confirmRegistration;

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

//https://medium.com/@kamer.dev/spring-boot-user-registration-and-login-43a33ea19745
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@Autowired
	private EmailSenderService emailSenderService;
	

	public void sendConfirmationMail(String userMail, String token) {

		final SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setTo(userMail);
		mailMessage.setSubject("Mail Confirmation Link!");
		mailMessage.setFrom("beatricebaldassarre86@gmail.com");
		mailMessage.setText( "Thank you for registering. Please click on the below link to activate your account.\n" 
						+ "http://localhost:8080/sign-up/confirm?token="
						+ token);

		emailSenderService.sendEmail(mailMessage);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		final Optional<User> optionalUser = userRepository.findByMail(email);
		
		return optionalUser.orElseThrow(() -> new UsernameNotFoundException
			(MessageFormat.format("User with email {0} cannot be found.", email)));

	}

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
	
	public void signUpUser(User user) {

		Optional<User> existingUser = userRepository.findByMail(user.getMail());
		if (!existingUser.isPresent()) {
			final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			userRepository.save(user);
	
			final ConfirmationToken confirmationToken = new ConfirmationToken(user);
			confirmationTokenService.saveConfirmationToken(confirmationToken);
			sendConfirmationMail(user.getMail(), confirmationToken.getConfirmationToken());
		}
		else System.out.println("User already exists!");

	}
	
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

	public void confirmUser(ConfirmationToken confirmationToken) {
		
		final User user = confirmationToken.getUser();
		user.setEnabled(true);
		userRepository.save(user);
		confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());

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
	
}

