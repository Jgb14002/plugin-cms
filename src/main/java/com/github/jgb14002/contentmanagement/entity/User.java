package com.github.jgb14002.contentmanagement.entity;

import com.github.jgb14002.contentmanagement.security.UserRole;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity(name = "users")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private UUID userId;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "account_locked")
	private boolean locked;

	@ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<UserRole> roles;

	@Column(name = "creation_date", nullable = false, updatable = false)
	private LocalDateTime creationDate;

	@Column(name = "creation_addr", nullable = false, updatable = false)
	private String creationAddr;

	public User() {}

	public User(String username, String email, String password, String creationAddr)
	{
		this.username = username;
		this.email = email;
		this.password = password;
		this.creationDate = LocalDateTime.now();
		this.creationAddr = creationAddr;
		this.roles = Set.of(UserRole.UNVERIFIED_USER);
	}
}
