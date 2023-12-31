package com.suresh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suresh.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUserNameOrEmail(String userName, String email);

	Optional<User> findByUserName(String userName);

	boolean existsByUserName(String userName);

	boolean existsByEmail(String email);
}
