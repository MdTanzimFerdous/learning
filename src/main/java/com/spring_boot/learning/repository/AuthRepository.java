package com.spring_boot.learning.repository;

import com.spring_boot.learning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String encode);

    Optional<User> findByEmail(String username);
}
