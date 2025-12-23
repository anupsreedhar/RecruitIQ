package com.zelexon.recruitiq.dao;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "interview_requests")
public class InterviewRequest {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    private String status; // REQUESTED, APPROVED, REJECTED, SCHEDULED
    private LocalDateTime requestedAt;
}

