package com.zelexon.recruitiq.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
import java.util.UUID;

@Schema(name = "CandidatePositionMatchDTO", description = "Candidate-to-position evaluation/match result (PII-safe)")
public class CandidatePositionMatchDTO {

    @Schema(description = "Candidate id", example = "1b2a3c4d-1111-2222-3333-444455556666")
    private UUID candidateId;

    @Schema(description = "Position id", example = "aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb")
    private UUID positionId;

    @Schema(description = "Overall alignment score", example = "82")
    private Integer alignmentScore;

    @Schema(description = "Strengths (JSON)", example = "{\"highlights\":[\"Strong Spring Boot\",\"Kafka experience\"]}")
    private Map<String, Object> strengths;

    @Schema(description = "Gaps (JSON)", example = "{\"missing\":[\"Kubernetes\"]}")
    private Map<String, Object> gaps;

    @Schema(description = "Interview focus areas (JSON)", example = "{\"topics\":[\"System design\",\"Concurrency\"]}")
    private Map<String, Object> interviewFocus;
}
