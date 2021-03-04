package com.github.jgb14002.contentmanagement.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.jgb14002.contentmanagement.entity.User;
import com.github.jgb14002.contentmanagement.exception.InvalidTokenException;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtService
{
	//TODO: Refresh tokens
	private final JwtConfig jwtConfig;

	@Autowired
	public JwtService(JwtConfig jwtConfig)
	{
		this.jwtConfig = jwtConfig;
	}

	public Optional<UUID> getUUIDFromToken(String token)
	{
		try
		{
			final String rawUUID = JWT.require(Algorithm.HMAC512(jwtConfig.getSecret()))
				.build()
				.verify(token)
				.getSubject();

			if (rawUUID != null)
			{
				return Optional.of(UUID.fromString(rawUUID));
			}
		}
		catch (JWTVerificationException ignored) {}

		throw new InvalidTokenException("The provided JWT is expired, revoked, malformed, or otherwise invalid");
	}

	public String issueDefaultTokenForUser(User user)
	{
		return JWT.create()
			.withSubject(user.getUserId().toString())
			.withExpiresAt(Date.from(Instant.now().plus(jwtConfig.getExpirationDuration())))
			.sign(Algorithm.HMAC512(jwtConfig.getSecret()));
	}
}
