package com.zelexon.recruitiq.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "resume_files")
public class ResumeFile {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Enumerated(EnumType.STRING)
    private FileType fileType; // ORIGINAL / REDACTED

    private String storageKey;
    private String mimeType;
    private Long fileSize;
    private String checksum;
    private LocalDateTime createdAt;
}
