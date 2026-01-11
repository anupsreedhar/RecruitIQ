package com.zelexon.recruitiq.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(name = "InterviewRequestDTO", description = "Interview request")
public class InterviewRequestDTO {

    @Schema(description = "Vendor id", example = "cccccccc-1111-2222-3333-dddddddddddd")
    private UUID vendorId;

    @Schema(description = "Candidate id", example = "1b2a3c4d-1111-2222-3333-444455556666")
    private UUID candidateId;

    @Schema(description = "Position id", example = "aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb")
    private UUID positionId;

    @Schema(description = "Request status", example = "REQUESTED")
    private String status;

    @Schema(description = "When it was requested", example = "2025-12-24T10:15:30")
    private LocalDateTime requestedAt;
}
