package com.projectmanagementthesis.repositories;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.projectmanagementthesis.service.registration.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository <ConfirmationToken,Long>{

	Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String token);

}
