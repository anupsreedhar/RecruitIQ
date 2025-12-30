package com.zelexon.recruitiq.repository;

import com.zelexon.recruitiq.dao.CandidatePii;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidatePiiRepository extends JpaRepository<CandidatePii, UUID> {
    Optional<CandidatePii> findByCandidate_Id(UUID candidateId);

    Optional<CandidatePii> findByEmailIgnoreCase(String email);
}
