package com.zelexon.recruitiq.dto;

import com.zelexon.recruitiq.dao.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(name = "UserDTO", description = "User representation (contains PII: email). Use only for internal/admin APIs")
public class UserDTO {

    @Schema(description = "User unique identifier", example = "99999999-1111-2222-3333-aaaaaaaaaaaa")
    private UUID id;

    @Schema(description = "User email (PII)", example = "admin@recrutiq.com")
    private String email;

    @Schema(description = "User role", example = "AGENCY_ADMIN")
    private Role role;

    @Schema(description = "User status", example = "ACTIVE")
    private String status;

    // Constructors
    public UserDTO() {
    }

    public UserDTO(UUID id, String email, Role role, String status) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    // Getters & Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
