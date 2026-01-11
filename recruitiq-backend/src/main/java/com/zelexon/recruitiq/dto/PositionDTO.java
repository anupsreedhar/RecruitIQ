package com.zelexon.recruitiq.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Schema(name = "PositionDTO", description = "Position/job description representation")
public class PositionDTO {

    @Schema(description = "Position unique identifier", example = "aaaaaaaa-1111-2222-3333-bbbbbbbbbbbb")
    private UUID id;

    @Schema(description = "Position title", example = "Senior Java Engineer")
    private String title;

    @Schema(description = "Job description text", example = "We are looking for a Java engineer with Spring Boot experience...")
    private String jdText;

    @Schema(description = "Structured JD (JSON)", example = "{\"mustHave\":[\"Java\",\"Spring Boot\"],\"niceToHave\":[\"AWS\"]}")
    private Map<String, Object> jdStructured;

    @Schema(description = "Position status", example = "OPEN")
    private String status;
}
