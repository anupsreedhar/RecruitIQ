package com.zelexon.recruitiq.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import java.util.UUID;

@Schema(name = "CandidateDTO", description = "Candidate summary (PII-safe)")
public class CandidateDTO {

    @Schema(description = "Candidate unique identifier", example = "1b2a3c4d-1111-2222-3333-444455556666")
    private UUID id;

    @Schema(description = "Internal candidate code", example = "CAND-000123")
    private String candidateCode;

    @Schema(description = "Total experience in years", example = "7")
    private Integer totalExperienceYears;

    @Schema(description = "Primary skills (structured)", example = "{\"java\":\"advanced\",\"spring\":\"advanced\"}")
    private Map<String, Object> primarySkills;

    @Schema(description = "Candidate location (coarse)", example = "Bengaluru")
    private String location;
}
