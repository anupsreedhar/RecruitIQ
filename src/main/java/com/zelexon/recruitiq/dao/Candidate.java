package com.zelexon.recruitiq.dao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    private UUID id;
    private String candidateCode;
    private Integer totalExperienceYears;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> primarySkills;

    private String location;
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
    private CandidatePii pii;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<ResumeFile> resumes;
}
