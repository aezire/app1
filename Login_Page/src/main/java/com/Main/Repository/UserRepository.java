package com.Main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Main.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
