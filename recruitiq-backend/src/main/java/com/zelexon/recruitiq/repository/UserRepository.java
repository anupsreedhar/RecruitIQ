package com.zelexon.recruitiq.repository;

import com.zelexon.recruitiq.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByIdAndActiveTrue(UUID id);
    Optional<User> findByEmailAndActiveTrue(String email);
    Optional<User> findByUsernameAndActiveTrue(String username);
    Optional<User> findByEmailOrUsernameAndActiveTrue(String email, String username);
    Optional<User> findByEmailAndRole(String email, String role);
    Optional<User> findByUsernameAndRole(String username, String role);
    Optional<User> findByEmailAndActiveTrueAndRole(String email, String role);
    Optional<User> findByUsernameAndActiveTrueAndRole(String username, String role);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmailOrUsername(String email, String username);
    long countByRole(String role);
    long countByActiveTrue();
    long countByRoleAndActiveTrue(String role);
    List<User> findAllByRole(String role);
    List<User> findAllByActiveTrue();
    List<User> findAllByRoleAndActiveTrue(String role);
}