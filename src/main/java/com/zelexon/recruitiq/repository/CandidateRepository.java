package com.zelexon.recruitiq.repository;


import com.zelexon.recruitiq.dao.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    Optional<Candidate> findByCandidateCode(String candidateCode);
}