package com.zelexon.recruitiq.repository;

import com.zelexon.recruitiq.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);


    Optional<User> findByEmailAndStatusTrue(String email);
   Optional<User> findByEmailAndRole(String email, String role);

    Optional<User> findByEmailAndStatusTrueAndRole(String email, String role);

    boolean existsByEmail(String email);

    long countByRole(String role);

    List<User> findAllByRole(String role);

}