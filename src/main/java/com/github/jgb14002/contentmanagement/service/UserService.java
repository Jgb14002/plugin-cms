package com.github.jgb14002.contentmanagement.service;

import com.github.jgb14002.contentmanagement.model.UserRegistrationDTO;
import com.github.jgb14002.contentmanagement.entity.User;
import com.github.jgb14002.contentmanagement.exception.UserOrEmailAlreadyExistsException;
import com.github.jgb14002.contentmanagement.repository.UserRepository;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserService
{
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository repository, PasswordEncoder passwordEncoder)
	{
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	public User registerUser(@Valid UserRegistrationDTO registrationDetails, String netAddr)
	{
		if (repository.existsByEmailOrUsername(registrationDetails.getEmail(), registrationDetails.getUsername()))
		{
			throw new UserOrEmailAlreadyExistsException("The provided username or email is already registered.");
		}

		String password = passwordEncoder.encode(registrationDetails.getPassword());
		User user = new User(
			registrationDetails.getUsername(),
			registrationDetails.getEmail(),
			password,
			netAddr
		);
		return repository.save(user);
	}

	public Optional<User> getUserByUsername(String username)
	{
		return repository.findByUsername(username);
	}
}
