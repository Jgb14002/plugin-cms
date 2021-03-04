package com.github.jgb14002.contentmanagement.repository;

import com.github.jgb14002.contentmanagement.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>
{
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	Optional<User> findByEmailOrUsername(String email, String username);

	boolean existsByEmailOrUsername(String email, String username);
}
