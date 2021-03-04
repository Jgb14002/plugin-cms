package com.github.jgb14002.contentmanagement.controller;

import com.github.jgb14002.contentmanagement.model.UserRegistrationDTO;
import com.github.jgb14002.contentmanagement.model.UserRegistrationResponseDTO;
import com.github.jgb14002.contentmanagement.entity.User;
import com.github.jgb14002.contentmanagement.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController
{
	private final UserService userService;

	@Autowired
	public UserController(UserService userService)
	{
		this.userService = userService;
	}

	@PostMapping("/")
	public ResponseEntity<UserRegistrationResponseDTO> registerUser(HttpServletRequest request,
																	@RequestBody UserRegistrationDTO registrationDetails)
	{
		User newUser = userService.registerUser(registrationDetails, request.getRemoteAddr());
		UserRegistrationResponseDTO responseData = new UserRegistrationResponseDTO(
			newUser.getUserId(),
			newUser.getEmail(),
			newUser.getUsername()
		);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
	}
}
