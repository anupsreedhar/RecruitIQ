package com.zelexon.recruitiq.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "vendor_activity_logs")
public class VendorActivityLog {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private String action; // VIEWED, REQUESTED_INTERVIEW
    private LocalDateTime createdAt;
}

