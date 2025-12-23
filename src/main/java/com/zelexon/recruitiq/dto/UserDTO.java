package com.zelexon.recruitiq.dto;

import com.zelexon.recruitiq.dao.Role;

import java.util.UUID;


public class UserDTO {
    private UUID id;
    private String email;
    private Role role;
    private String status;

    // Constructors
    public UserDTO() {}

    public UserDTO(UUID id, String email, Role role, String status) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
