package com.zelexon.recruitiq.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "ApiResponseWrapper", description = "Standard success response wrapper")
public class ApiResponseWrapper<T> {

    @Schema(description = "Whether the request was successful", example = "true")
    private boolean success;

    @Schema(description = "Response payload")
    private T data;

    @Schema(description = "Optional message", example = "Candidate created")
    private String message;

    @Schema(description = "Timestamp of the response (UTC)", example = "2025-12-24T10:15:30Z")
    private Instant timestamp;

    public ApiResponseWrapper() {
    }

    public ApiResponseWrapper(boolean success, T data, String message, Instant timestamp) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.timestamp = timestamp;
    }

    public static <T> ApiResponseWrapper<T> ok(T data, String message) {
        return new ApiResponseWrapper<>(true, data, message, Instant.now());
    }

    public static <T> ApiResponseWrapper<T> error(String message) {
        return new ApiResponseWrapper<>(false, null, message, Instant.now());
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
}
