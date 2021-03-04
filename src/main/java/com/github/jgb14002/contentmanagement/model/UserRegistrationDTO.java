package com.github.jgb14002.contentmanagement.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRegistrationDTO
{
	@Email(message = "An invalid email address was provided")
	private final String email;

	@NotEmpty(message = "No username was provided")
	private final String username;

	@Length(min = 6, message = "Password must be at least {min} characters")
	private final String password;
}
