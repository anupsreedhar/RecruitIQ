package com.zelexon.recruitiq.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.UUID;

@Entity
@Table(name = "candidate_pii")
public class CandidatePii {
    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private String fullName;
    private String email;
    private String phone;
    private String linkedinUrl;
}
