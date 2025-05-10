package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.User;
import java.util.List;import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
	   boolean existsByEmail(String email);
	    boolean existsByUsername(String username);
	    Optional<User> findByUsername(String username);
}
