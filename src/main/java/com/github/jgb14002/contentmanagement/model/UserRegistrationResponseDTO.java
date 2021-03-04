package com.github.jgb14002.contentmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Data;

@Data
public class UserRegistrationResponseDTO
{
	@JsonProperty("id")
	private final UUID userId;
	private final String email;
	private final String username;
}
