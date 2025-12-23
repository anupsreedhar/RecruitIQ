package com.zelexon.recruitiq.dto;


public class CandidatePositionMatchDTO {
    private UUID candidateId;
    private UUID positionId;
    private Integer alignmentScore;
    private Map<String, Object> strengths;
    private Map<String, Object> gaps;
    private Map<String, Object> interviewFocus;
}
