package com.zelexon.recruitiq.dao;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;

@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    private UUID id;
    private String candidateCode;
    private Integer totalExperienceYears;
    @Type(type = "jsonb")
    private Map<String, Object> primarySkills;
    private String location;
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
    private CandidatePii pii;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<ResumeFile> resumes;
}

