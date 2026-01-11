package com.zelexon.recruitiq.service;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(String email, String rawPassword, String role) {
        // Check if email already exists
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }
        User user = new User();
        user.setEmail(email);

        // Hash the password (use BCrypt or any secure method)
        user.setPasswordHash(hashPassword(rawPassword));

        user.setRole(role);
        user.setStatus("ACTIVE");  // default, can also omit
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        return userRepository.save(user);
}
