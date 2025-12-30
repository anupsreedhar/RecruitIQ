package com.zelexon.recruitiq.controller;

import com.zelexon.recruitiq.dto.ApiErrorResponse;
import com.zelexon.recruitiq.dto.ApiResponseWrapper;
import com.zelexon.recruitiq.dto.VendorCandidateVisibilityDTO;
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
@RequestMapping("/api/v1/vendor-visibility")
@Tag(name = "Vendor Visibility", description = "Controls which candidates a vendor can see (PII-safe)")
public class VendorVisibilityController {

    @GetMapping("/vendor/{vendorId}")
    @Operation(summary = "List visible candidates for vendor", description = "Lists candidate visibility entries for a vendor. Does not expose PII.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class), examples = @ExampleObject(
                    name = "SuccessResponse",
                    value = "{\"success\":true,\"message\":\"OK\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":[{\"vendorId\":\"cccccccc-1111-2222-3333-dddddddddddd\",\"candidateId\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"positionId\":\"aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb\",\"publishedAt\":\"2025-12-24T10:15:30\"}]}"
            ))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<List<VendorCandidateVisibilityDTO>>> listVisibilityForVendor(@PathVariable UUID vendorId) {
        return ResponseEntity.ok(ApiResponseWrapper.ok(List.of(), "OK"));
    }

    @PostMapping
    @Operation(summary = "Publish candidate to vendor", description = "Publishes a candidate to a vendor for a position (PII-safe).")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class), examples = @ExampleObject(
                    name = "CreatedResponse",
                    value = "{\"success\":true,\"message\":\"Published\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"vendorId\":\"cccccccc-1111-2222-3333-dddddddddddd\",\"candidateId\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"positionId\":\"aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb\",\"publishedAt\":\"2025-12-24T10:15:30\"}}"
            ))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<VendorCandidateVisibilityDTO>> publishCandidate(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Publish payload",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendorCandidateVisibilityDTO.class), examples = @ExampleObject(
                            name = "PublishRequest",
                            value = "{\"vendorId\":\"cccccccc-1111-2222-3333-dddddddddddd\",\"candidateId\":\"1b2a3c4d-1111-2222-3333-444455556666\",\"positionId\":\"aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb\"}"
                    ))
            )
            @RequestBody VendorCandidateVisibilityDTO request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseWrapper.ok(request, "Published"));
    }
}
