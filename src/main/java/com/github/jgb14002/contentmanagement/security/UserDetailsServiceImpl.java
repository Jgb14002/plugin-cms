package com.github.jgb14002.contentmanagement.security;

import com.github.jgb14002.contentmanagement.entity.User;
import com.github.jgb14002.contentmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService
{
	private final UserService userService;

	@Autowired
	public UserDetailsServiceImpl(UserService userService)
	{
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userService.getUserByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("No user found for username: " + username));

		var userRoles = user.getRoles().stream()
			.map(Enum::name)
			.toArray(String[]::new);

		var userBuilder = org.springframework.security.core.userdetails.User.builder();

		return userBuilder.username(user.getUsername())
			.password(user.getPassword())
			.roles(userRoles)
			.accountLocked(user.isLocked())
			.build();
	}
}
