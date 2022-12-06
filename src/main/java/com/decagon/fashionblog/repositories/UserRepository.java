package com.decagon.fashionblog.repositories;

import com.decagon.fashionblog.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByUsernameAndPassword(String username, String password);
}
