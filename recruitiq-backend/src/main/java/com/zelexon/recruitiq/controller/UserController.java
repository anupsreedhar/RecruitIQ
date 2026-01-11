package com.zelexon.recruitiq.controller;

import com.zelexon.recruitiq.dto.ApiErrorResponse;
import com.zelexon.recruitiq.dto.ApiResponseWrapper;
import com.zelexon.recruitiq.dto.UserDTO;
import com.zelexon.recruitiq.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "User management APIs (internal/admin)")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    @Operation(summary = "List users", description = "Lists users (admin/internal). Contains email (PII) and must not be exposed to vendors.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class), examples = @ExampleObject(
                    name = "SuccessResponse",
                    value = "{\"success\":true,\"message\":\"OK\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":[{\"id\":\"99999999-1111-2222-3333-aaaaaaaaaaaa\",\"email\":\"admin@recrutiq.com\",\"role\":\"AGENCY_ADMIN\",\"status\":\"ACTIVE\"}]}"
            ))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<List<UserDTO>>> listUsers() {
        return ResponseEntity.ok(ApiResponseWrapper.ok(List.of(), "OK"));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by id", description = "Returns a user by id (admin/internal).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class), examples = @ExampleObject(
                    name = "SuccessResponse",
                    value = "{\"success\":true,\"message\":\"OK\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"id\":\"99999999-1111-2222-3333-aaaaaaaaaaaa\",\"email\":\"admin@recrutiq.com\",\"role\":\"AGENCY_ADMIN\",\"status\":\"ACTIVE\"}}"
            ))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), examples = @ExampleObject(value = "{\"code\":\"NOT_FOUND\",\"message\":\"User not found\",\"timestamp\":\"2025-12-24T10:15:30Z\"}"))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<UserDTO>> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok(ApiResponseWrapper.ok(new UserDTO(), "OK"));
    }

    @PostMapping
    @Operation(summary = "Create user", description = "Creates a user (admin/internal).")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class), examples = @ExampleObject(
                    name = "CreatedResponse",
                    value = "{\"success\":true,\"message\":\"User created\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"id\":\"99999999-1111-2222-3333-aaaaaaaaaaaa\",\"email\":\"recruiter@recrutiq.com\",\"role\":\"RECRUITER\",\"status\":\"ACTIVE\"}}"
            ))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<UserDTO>> createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "User payload (include password)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class), examples = @ExampleObject(
                            name = "CreateUserRequest",
                            value = "{\"email\":\"recruiter@recrutiq.com\",\"role\":\"RECRUITER\",\"status\":\"ACTIVE\",\"password\":\"Secret123!\"}"
                    ))
            )
            @RequestBody Map<String, String> request
    ) {
        // Validate required fields
        String email = request.get("email");
        String roleStr = request.get("role");
        String status = request.getOrDefault("status", "ACTIVE");
        String password = request.get("password");
        if (email == null || roleStr == null || password == null) {
            return ResponseEntity.badRequest().body(ApiResponseWrapper.error("Missing required fields: email, role, password"));
        }

        // Check if user already exists
        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponseWrapper.error("User already exists"));
        }

        // Create and save user
        com.zelexon.recruitiq.dao.User user = new com.zelexon.recruitiq.dao.User();
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        final com.zelexon.recruitiq.dao.Role role;
        try {
            role = com.zelexon.recruitiq.dao.Role.valueOf(roleStr);
            user.setRole(role);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponseWrapper.error("Invalid role"));
        }
        user.setStatus(status);
        userRepository.save(user);

        UserDTO dto = new UserDTO(user.getId(), user.getEmail(), role, user.getStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseWrapper.ok(dto, "User created"));
    }

    @PatchMapping("/{userId}/status")
    @Operation(summary = "Update user status", description = "Updates user status (admin/internal).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class), examples = @ExampleObject(
                    name = "SuccessResponse",
                    value = "{\"success\":true,\"message\":\"Status updated\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"status\":\"INACTIVE\"}}"
            ))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<UserDTO>> updateUserStatus(
            @PathVariable UUID userId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Status patch payload",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class), examples = @ExampleObject(value = "{\"status\":\"INACTIVE\"}"))
            )
            @RequestBody Map<String, String> body
    ) {
        return ResponseEntity.ok(ApiResponseWrapper.ok(new UserDTO(), "Status updated"));
    }
}
