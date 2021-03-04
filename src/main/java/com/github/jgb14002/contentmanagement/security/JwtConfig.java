package com.github.jgb14002.contentmanagement.security;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.convert.DurationUnit;

@Data
@ConstructorBinding
@ConfigurationProperties("jwt")
public class JwtConfig
{
	public static final String AUTH_HEADER_KEY = "Authorization";
	public static final String AUTH_HEADER_PREFIX = "Bearer";

	private final String secret;

	@DurationUnit(ChronoUnit.SECONDS)
	private final Duration expirationDuration;

	@DurationUnit(ChronoUnit.SECONDS)
	private final Duration refreshExpirationDuration;

	public JwtConfig(String secret, Duration expirationDuration, Duration refreshExpirationDuration)
	{
		this.secret = secret;
		this.expirationDuration = expirationDuration;
		this.refreshExpirationDuration = refreshExpirationDuration;
	}
}
