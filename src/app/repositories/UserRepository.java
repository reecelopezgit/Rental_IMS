package app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // Find user by username
    User findByToken(String token);

}
