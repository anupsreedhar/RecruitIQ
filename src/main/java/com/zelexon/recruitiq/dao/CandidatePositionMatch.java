package com.zelexon.recruitiq.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
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

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> strengths;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> gaps;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> interviewFocus;

    private String internalNotes;
    private LocalDateTime createdAt;
}
