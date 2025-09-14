package org.lessons.java.spring_cineteca.repository;

import java.util.Optional;

import org.lessons.java.spring_cineteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByusername(String username);
}
