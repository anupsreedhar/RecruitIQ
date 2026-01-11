package com.zelexon.recruitiq.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(name = "VendorActivityLogDTO", description = "Vendor activity log record")
public class VendorActivityLogDTO {

    @Schema(description = "Vendor id", example = "cccccccc-1111-2222-3333-dddddddddddd")
    private UUID vendorId;

    @Schema(description = "Candidate id (no PII)", example = "1b2a3c4d-1111-2222-3333-444455556666")
    private UUID candidateId;

    @Schema(description = "Action", example = "VIEWED")
    private String action;

    @Schema(description = "When the event occurred", example = "2025-12-24T10:15:30")
    private LocalDateTime createdAt;
}
