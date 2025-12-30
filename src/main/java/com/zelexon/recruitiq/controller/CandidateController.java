package com.zelexon.recruitiq.controller;

import com.zelexon.recruitiq.dto.ApiErrorResponse;
import com.zelexon.recruitiq.dto.ApiResponseWrapper;
import com.zelexon.recruitiq.dto.CandidateDTO;
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
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/candidates")
@Tag(name = "Candidates", description = "Candidate APIs (PII-safe)")
public class CandidateController {

    @GetMapping
    @Operation(
            summary = "List candidates",
            description = "Returns a pageless list of candidates. This endpoint is PII-safe and does not include email/phone/fullName."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWrapper.class),
                            examples = @ExampleObject(
                                    name = "SuccessResponse",
                                    value = "{\"success\":true,\"message\":\"OK\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":[{\"id\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"candidateCode\":\"CAND-000123\",\"totalExperienceYears\":7,\"primarySkills\":{\"java\":\"advanced\"},\"location\":\"Bengaluru\"}]}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<ApiResponseWrapper<List<CandidateDTO>>> listCandidates() {
        // TODO wire CandidateService; stubbed for contract generation.
        return ResponseEntity.ok(ApiResponseWrapper.ok(List.of(), "OK"));
    }

    @GetMapping("/{candidateId}")
    @Operation(
            summary = "Get candidate by id",
            description = "Returns a candidate summary by id (PII-safe)."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWrapper.class),
                            examples = @ExampleObject(
                                    name = "SuccessResponse",
                                    value = "{\"success\":true,\"message\":\"OK\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"id\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"candidateCode\":\"CAND-000123\",\"totalExperienceYears\":7,\"primarySkills\":{\"java\":\"advanced\"},\"location\":\"Bengaluru\"}}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), examples = @ExampleObject(value = "{\"code\":\"NOT_FOUND\",\"message\":\"Candidate not found\",\"timestamp\":\"2025-12-24T10:15:30Z\"}"))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<ApiResponseWrapper<CandidateDTO>> getCandidateById(
            @PathVariable UUID candidateId
    ) {
        // TODO wire CandidateService; stubbed for contract generation.
        CandidateDTO dto = new CandidateDTO();
        return ResponseEntity.ok(ApiResponseWrapper.ok(dto, "OK"));
    }

    @PostMapping
    @Operation(
            summary = "Create candidate",
            description = "Creates a new candidate record. Request/response are PII-safe at this API layer."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWrapper.class),
                            examples = @ExampleObject(
                                    name = "CreatedResponse",
                                    value = "{\"success\":true,\"message\":\"Candidate created\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"id\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"candidateCode\":\"CAND-000124\",\"totalExperienceYears\":5,\"primarySkills\":{\"java\":\"intermediate\"},\"location\":\"Hyderabad\"}}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), examples = @ExampleObject(value = "{\"code\":\"VALIDATION_ERROR\",\"message\":\"candidateCode must not be blank\",\"timestamp\":\"2025-12-24T10:15:30Z\"}"))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<ApiResponseWrapper<CandidateDTO>> createCandidate(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Candidate payload (PII-safe)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CandidateDTO.class),
                            examples = @ExampleObject(
                                    name = "CreateCandidateRequest",
                                    value = "{\"candidateCode\":\"CAND-000124\",\"totalExperienceYears\":5,\"primarySkills\":{\"java\":\"intermediate\"},\"location\":\"Hyderabad\"}"
                            )
                    )
            )
            @RequestBody CandidateDTO request
    ) {
        // TODO wire CandidateService; stubbed for contract generation.
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseWrapper.ok(request, "Candidate created"));
    }

    @GetMapping("/by-code/{candidateCode}")
    @Operation(
            summary = "Get candidate by code",
            description = "Returns candidate summary by internal candidate code (PII-safe)."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWrapper.class),
                            examples = @ExampleObject(
                                    name = "SuccessResponse",
                                    value = "{\"success\":true,\"message\":\"OK\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"id\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"candidateCode\":\"CAND-000123\",\"totalExperienceYears\":7,\"primarySkills\":{\"java\":\"advanced\"},\"location\":\"Bengaluru\"}}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<ApiResponseWrapper<CandidateDTO>> getCandidateByCode(
            @PathVariable String candidateCode
    ) {
        CandidateDTO dto = new CandidateDTO();
        return ResponseEntity.ok(ApiResponseWrapper.ok(dto, "OK"));
    }

    @PatchMapping("/{candidateId}/skills")
    @Operation(
            summary = "Update candidate primary skills",
            description = "Partially updates candidate primary skills map. PII-safe."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWrapper.class),
                            examples = @ExampleObject(
                                    name = "SuccessResponse",
                                    value = "{\"success\":true,\"message\":\"Skills updated\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"id\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"candidateCode\":\"CAND-000123\",\"totalExperienceYears\":7,\"primarySkills\":{\"java\":\"advanced\",\"spring\":\"advanced\"},\"location\":\"Bengaluru\"}}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<ApiResponseWrapper<CandidateDTO>> updateCandidateSkills(
            @PathVariable UUID candidateId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "JSON object representing primary skills",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class),
                            examples = @ExampleObject(
                                    name = "UpdateSkillsRequest",
                                    value = "{\"java\":\"advanced\",\"spring\":\"advanced\"}"
                            )
                    )
            )
            @RequestBody Map<String, Object> primarySkills
    ) {
        CandidateDTO dto = new CandidateDTO();
        return ResponseEntity.ok(ApiResponseWrapper.ok(dto, "Skills updated"));
    }
}
