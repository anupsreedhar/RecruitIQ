package com.zelexon.recruitiq.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "ApiErrorResponse", description = "Standard error response body")
public class ApiErrorResponse {

    @Schema(description = "Machine-readable error code", example = "VALIDATION_ERROR")
    private String code;

    @Schema(description = "Human-readable error message", example = "Request validation failed")
    private String message;

    @Schema(description = "Timestamp when the error occurred (UTC)", example = "2025-12-24T10:15:30Z")
    private Instant timestamp;

    @Schema(description = "Optional additional details (safe to expose)", example = "candidateCode must not be blank")
    private String details;

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(String code, String message, Instant timestamp, String details) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

