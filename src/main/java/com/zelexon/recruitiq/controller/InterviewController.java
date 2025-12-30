package com.zelexon.recruitiq.controller;

import com.zelexon.recruitiq.dto.ApiErrorResponse;
import com.zelexon.recruitiq.dto.ApiResponseWrapper;
import com.zelexon.recruitiq.dto.InterviewRequestDTO;
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
@RequestMapping("/api/v1/interviews")
@Tag(name = "Interviews", description = "Interview request APIs")
public class InterviewController {

    @GetMapping
    @Operation(summary = "List interview requests", description = "Lists interview requests. Optionally filter by vendorId or status.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWrapper.class),
                            examples = @ExampleObject(
                                    name = "SuccessResponse",
                                    value = "{\"success\":true,\"message\":\"OK\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":[{\"vendorId\":\"cccccccc-1111-2222-3333-dddddddddddd\",\"candidateId\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"positionId\":\"aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb\",\"status\":\"REQUESTED\",\"requestedAt\":\"2025-12-24T10:15:30\"}]}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<List<InterviewRequestDTO>>> listInterviewRequests(
            @RequestParam(required = false) UUID vendorId,
            @RequestParam(required = false) String status
    ) {
        return ResponseEntity.ok(ApiResponseWrapper.ok(List.of(), "OK"));
    }

    @PostMapping
    @Operation(summary = "Create interview request", description = "Creates a new interview request for a vendor/candidate/position.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWrapper.class),
                            examples = @ExampleObject(
                                    name = "CreatedResponse",
                                    value = "{\"success\":true,\"message\":\"Interview requested\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"vendorId\":\"cccccccc-1111-2222-3333-dddddddddddd\",\"candidateId\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"positionId\":\"aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb\",\"status\":\"REQUESTED\",\"requestedAt\":\"2025-12-24T10:15:30\"}}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<InterviewRequestDTO>> createInterviewRequest(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Interview request payload",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = InterviewRequestDTO.class),
                            examples = @ExampleObject(
                                    name = "CreateInterviewRequest",
                                    value = "{\"vendorId\":\"cccccccc-1111-2222-3333-dddddddddddd\",\"candidateId\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"positionId\":\"aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb\",\"status\":\"REQUESTED\"}"
                            )
                    )
            )
            @RequestBody InterviewRequestDTO request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseWrapper.ok(request, "Interview requested"));
    }

    @PatchMapping("/{requestId}/status")
    @Operation(summary = "Update interview request status", description = "Updates status of an interview request.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class), examples = @ExampleObject(
                            name = "SuccessResponse",
                            value = "{\"success\":true,\"message\":\"Status updated\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"status\":\"APPROVED\"}}"
                    ))
            ),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<InterviewRequestDTO>> updateInterviewStatus(
            @PathVariable UUID requestId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Status patch payload",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = java.util.Map.class), examples = @ExampleObject(value = "{\"status\":\"APPROVED\"}"))
            )
            @RequestBody java.util.Map<String, String> body
    ) {
        return ResponseEntity.ok(ApiResponseWrapper.ok(new InterviewRequestDTO(), "Status updated"));
    }
}
