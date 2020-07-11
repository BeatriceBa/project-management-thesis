package com.projectmanagementthesis.service.confirmRegistration;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projectmanagementthesis.repositories.ConfirmationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
	
	private final ConfirmationTokenRepository confirmationTokenRepository;
	
	public void saveConfirmationToken(ConfirmationToken confirmationToken) {
		confirmationTokenRepository.save(confirmationToken);
	}
	
	public void deleteConfirmationToken(Long id) {
		confirmationTokenRepository.deleteById(id);
	}
	
	public Optional<ConfirmationToken> findConfirmationTokenByToken(String token) {

		return confirmationTokenRepository.findConfirmationTokenByConfirmationToken(token);
	}

	
}
