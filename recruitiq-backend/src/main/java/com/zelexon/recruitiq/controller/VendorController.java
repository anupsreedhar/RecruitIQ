package com.zelexon.recruitiq.controller;

import com.zelexon.recruitiq.dto.ApiErrorResponse;
import com.zelexon.recruitiq.dto.ApiResponseWrapper;
import com.zelexon.recruitiq.dto.VendorDTO;
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
@RequestMapping("/api/v1/vendors")
@Tag(name = "Vendors", description = "Vendor management APIs")
public class VendorController {

    @GetMapping
    @Operation(summary = "List vendors", description = "Lists vendors.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class), examples = @ExampleObject(
                    name = "SuccessResponse",
                    value = "{\"success\":true,\"message\":\"OK\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":[{\"id\":\"cccccccc-1111-2222-3333-dddddddddddd\",\"name\":\"Acme Staffing\"}]}"
            ))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<List<VendorDTO>>> listVendors() {
        return ResponseEntity.ok(ApiResponseWrapper.ok(List.of(), "OK"));
    }

    @GetMapping("/{vendorId}")
    @Operation(summary = "Get vendor by id", description = "Returns vendor by id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class), examples = @ExampleObject(
                    name = "SuccessResponse",
                    value = "{\"success\":true,\"message\":\"OK\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"id\":\"cccccccc-1111-2222-3333-dddddddddddd\",\"name\":\"Acme Staffing\"}}"
            ))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class), examples = @ExampleObject(value = "{\"code\":\"NOT_FOUND\",\"message\":\"Vendor not found\",\"timestamp\":\"2025-12-24T10:15:30Z\"}"))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<VendorDTO>> getVendorById(@PathVariable UUID vendorId) {
        return ResponseEntity.ok(ApiResponseWrapper.ok(new VendorDTO(), "OK"));
    }

    @PostMapping
    @Operation(summary = "Create vendor", description = "Creates a vendor.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWrapper.class), examples = @ExampleObject(
                    name = "CreatedResponse",
                    value = "{\"success\":true,\"message\":\"Vendor created\",\"timestamp\":\"2025-12-24T10:15:30Z\",\"data\":{\"id\":\"cccccccc-1111-2222-3333-dddddddddddd\",\"name\":\"Acme Staffing\"}}"
            ))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    public ResponseEntity<ApiResponseWrapper<VendorDTO>> createVendor(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Vendor payload",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendorDTO.class), examples = @ExampleObject(
                            name = "CreateVendorRequest",
                            value = "{\"name\":\"Acme Staffing\"}"
                    ))
            )
            @RequestBody VendorDTO request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseWrapper.ok(request, "Vendor created"));
    }
}
