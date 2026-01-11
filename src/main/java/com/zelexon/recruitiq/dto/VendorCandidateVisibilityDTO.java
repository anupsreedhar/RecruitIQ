package com.zelexon.recruitiq.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(name = "VendorCandidateVisibilityDTO", description = "Represents which candidates are visible to a vendor for a position")
public class VendorCandidateVisibilityDTO {

    @Schema(description = "Vendor id", example = "cccccccc-1111-2222-3333-dddddddddddd")
    private UUID vendorId;

    @Schema(description = "Candidate id", example = "1b2a3c4d-1111-2222-3333-444455556666")
    private UUID candidateId;

    @Schema(description = "Position id", example = "aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb")
    private UUID positionId;

    @Schema(description = "When access was published", example = "2025-12-24T10:15:30")
    private LocalDateTime publishedAt;
}
