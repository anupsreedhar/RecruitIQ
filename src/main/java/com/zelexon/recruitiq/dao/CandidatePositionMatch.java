package com.zelexon.recruitiq.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "candidate_position_match")
public class CandidatePositionMatch {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    private Integer alignmentScore;

    @Type(type = "jsonb")
    private Map<String, Object> strengths;

    @Type(type = "jsonb")
    private Map<String, Object> gaps;

    @Type(type = "jsonb")
    private Map<String, Object> interviewFocus;

    private String internalNotes;
    private LocalDateTime createdAt;
}

