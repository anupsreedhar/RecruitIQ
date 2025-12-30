package com.zelexon.recruitiq.controller;

import com.zelexon.recruitiq.dto.ApiErrorResponse;
import com.zelexon.recruitiq.dto.ApiResponseWrapper;
import com.zelexon.recruitiq.dto.ResumeFileDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/resumes")
@Tag(name = "Resumes", description = "Resume APIs (agency/internal)")
public class ResumeController {

    @GetMapping("/candidate/{candidateId}")
    @Operation(summary = "List resumes for candidate", description = "Returns resume metadata for a candidate. For vendor-facing flows, use redacted resumes and do not expose direct PII.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWrapper.class),
                            examples = @ExampleObject(
                                    name = "SuccessResponse",
                                    value = "{\"success\":true,\"message\":\"OK\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":[{\"id\":\"eeeeeeee-1111-2222-3333-ffffffffffff\",\"fileType\":\"ORIGINAL\",\"downloadUrl\":\"https://minio.local/bucket/object?X-Amz-Signature=...\",\"mimeType\":\"application/pdf\"}]}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<List<ResumeFileDTO>>> listCandidateResumes(@PathVariable UUID candidateId) {
        return ResponseEntity.ok(ApiResponseWrapper.ok(List.of(), "OK"));
    }

    @PostMapping("/candidate/{candidateId}")
    @Operation(summary = "Upload resume for candidate", description = "Registers a resume file for a candidate (metadata-only contract). Actual upload may be handled via pre-signed URL in a real implementation.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWrapper.class),
                            examples = @ExampleObject(
                                    name = "CreatedResponse",
                                    value = "{\"success\":true,\"message\":\"Resume registered\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"id\":\"eeeeeeee-1111-2222-3333-ffffffffffff\",\"fileType\":\"ORIGINAL\",\"downloadUrl\":\"https://minio.local/bucket/object?X-Amz-Signature=...\",\"mimeType\":\"application/pdf\"}}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), examples = @ExampleObject(value = "{\"code\":\"VALIDATION_ERROR\",\"message\":\"mimeType must not be blank\",\"timestamp\":\"2025-12-24T10:15:30Z\"}"))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<ResumeFileDTO>> registerResume(
            @PathVariable UUID candidateId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Resume file metadata",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResumeFileDTO.class),
                            examples = @ExampleObject(
                                    name = "RegisterResumeRequest",
                                    value = "{\"fileType\":\"ORIGINAL\",\"mimeType\":\"application/pdf\"}"
                            )
                    )
            )
            @RequestBody ResumeFileDTO request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseWrapper.ok(request, "Resume registered"));
    }
}
