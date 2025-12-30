package com.zelexon.recruitiq.controller;

import com.zelexon.recruitiq.dto.ApiErrorResponse;
import com.zelexon.recruitiq.dto.ApiResponseWrapper;
import com.zelexon.recruitiq.dto.PositionDTO;
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
@RequestMapping("/api/v1/positions")
@Tag(name = "Positions", description = "Position APIs")
public class PositionController {

    @GetMapping
    @Operation(summary = "List positions", description = "Lists positions. Optionally filter by status using query param.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWrapper.class),
                            examples = @ExampleObject(
                                    name = "SuccessResponse",
                                    value = "{\"success\":true,\"message\":\"OK\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":[{\"id\":\"aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb\",\"title\":\"Senior Java Engineer\",\"jdText\":\"...\",\"jdStructured\":{\"mustHave\":[\"Java\",\"Spring Boot\"]},\"status\":\"OPEN\"}]}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<List<PositionDTO>>> listPositions(
            @RequestParam(required = false) String status
    ) {
        return ResponseEntity.ok(ApiResponseWrapper.ok(List.of(), "OK"));
    }

    @GetMapping("/{positionId}")
    @Operation(summary = "Get position by id", description = "Returns a single position by id.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWrapper.class),
                            examples = @ExampleObject(
                                    name = "SuccessResponse",
                                    value = "{\"success\":true,\"message\":\"OK\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"id\":\"aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb\",\"title\":\"Senior Java Engineer\",\"jdText\":\"...\",\"jdStructured\":{\"mustHave\":[\"Java\"]},\"status\":\"OPEN\"}}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), examples = @ExampleObject(value = "{\"code\":\"NOT_FOUND\",\"message\":\"Position not found\",\"timestamp\":\"2025-12-24T10:15:30Z\"}"))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<PositionDTO>> getPositionById(@PathVariable UUID positionId) {
        return ResponseEntity.ok(ApiResponseWrapper.ok(new PositionDTO(), "OK"));
    }

    @PostMapping
    @Operation(summary = "Create position", description = "Creates a new position.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWrapper.class),
                            examples = @ExampleObject(
                                    name = "CreatedResponse",
                                    value = "{\"success\":true,\"message\":\"Position created\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"id\":\"aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb\",\"title\":\"Senior Java Engineer\",\"jdText\":\"...\",\"jdStructured\":{\"mustHave\":[\"Java\"]},\"status\":\"OPEN\"}}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<PositionDTO>> createPosition(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Position payload",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PositionDTO.class),
                            examples = @ExampleObject(
                                    name = "CreatePositionRequest",
                                    value = "{\"title\":\"Senior Java Engineer\",\"jdText\":\"We are looking for...\",\"jdStructured\":{\"mustHave\":[\"Java\",\"Spring Boot\"]},\"status\":\"OPEN\"}"
                            )
                    )
            )
            @RequestBody PositionDTO request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseWrapper.ok(request, "Position created"));
    }

    @PatchMapping("/{positionId}/status")
    @Operation(summary = "Update position status", description = "Updates the status of a position.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWrapper.class),
                            examples = @ExampleObject(
                                    name = "SuccessResponse",
                                    value = "{\"success\":true,\"message\":\"Status updated\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"id\":\"aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb\",\"title\":\"Senior Java Engineer\",\"status\":\"CLOSED\"}}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<PositionDTO>> updatePositionStatus(
            @PathVariable UUID positionId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Status update payload",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class),
                            examples = @ExampleObject(name = "UpdateStatusRequest", value = "{\"status\":\"CLOSED\"}")
                    )
            )
            @RequestBody Map<String, String> body
    ) {
        return ResponseEntity.ok(ApiResponseWrapper.ok(new PositionDTO(), "Status updated"));
    }
}
