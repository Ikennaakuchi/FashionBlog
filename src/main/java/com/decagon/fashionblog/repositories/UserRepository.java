package com.decagon.fashionblog.repositories;

import com.decagon.fashionblog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsernameAndPassword(String username, String password);
    Optional<User> findUserByRole(String role);
}
