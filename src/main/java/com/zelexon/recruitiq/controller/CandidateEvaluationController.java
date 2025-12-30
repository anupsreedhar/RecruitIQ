package com.zelexon.recruitiq.controller;

import com.zelexon.recruitiq.dto.ApiErrorResponse;
import com.zelexon.recruitiq.dto.ApiResponseWrapper;
import com.zelexon.recruitiq.dto.CandidatePositionMatchDTO;
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
@RequestMapping("/api/v1/evaluations")
@Tag(name = "Evaluations", description = "Candidate evaluation APIs")
public class CandidateEvaluationController {

    @GetMapping("/candidate/{candidateId}")
    @Operation(summary = "List evaluations for candidate", description = "Lists evaluated position matches for a candidate (PII-safe).")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class), examples = @ExampleObject(
                            name = "SuccessResponse",
                            value = "{\"success\":true,\"message\":\"OK\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":[{\"candidateId\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"positionId\":\"aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb\",\"alignmentScore\":82,\"strengths\":{\"highlights\":[\"Spring Boot\"]},\"gaps\":{\"missing\":[\"Kubernetes\"]},\"interviewFocus\":{\"topics\":[\"System design\"]}}]}"
                    ))
            ),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<List<CandidatePositionMatchDTO>>> listCandidateEvaluations(@PathVariable UUID candidateId) {
        return ResponseEntity.ok(ApiResponseWrapper.ok(List.of(), "OK"));
    }

    @PostMapping
    @Operation(summary = "Create evaluation", description = "Creates a candidate-position evaluation/match result (PII-safe).")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class), examples = @ExampleObject(
                            name = "CreatedResponse",
                            value = "{\"success\":true,\"message\":\"Evaluation created\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"candidateId\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"positionId\":\"aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb\",\"alignmentScore\":82,\"strengths\":{\"highlights\":[\"Spring Boot\"]},\"gaps\":{\"missing\":[\"Kubernetes\"]},\"interviewFocus\":{\"topics\":[\"System design\"]}}}"
                    ))
            ),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<CandidatePositionMatchDTO>> createEvaluation(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Evaluation payload",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CandidatePositionMatchDTO.class), examples = @ExampleObject(
                            name = "CreateEvaluationRequest",
                            value = "{\"candidateId\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"positionId\":\"aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb\",\"alignmentScore\":82,\"strengths\":{\"highlights\":[\"Spring Boot\"]},\"gaps\":{\"missing\":[\"Kubernetes\"]},\"interviewFocus\":{\"topics\":[\"System design\"]}}"
                    ))
            )
            @RequestBody CandidatePositionMatchDTO request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseWrapper.ok(request, "Evaluation created"));
    }
}
