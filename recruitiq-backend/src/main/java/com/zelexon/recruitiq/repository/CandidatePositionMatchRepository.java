package com.zelexon.recruitiq.repository;

import com.zelexon.recruitiq.dao.CandidatePositionMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CandidatePositionMatchRepository extends JpaRepository<CandidatePositionMatch, UUID> {
    List<CandidatePositionMatch> findAllByCandidate_Id(UUID candidateId);

    List<CandidatePositionMatch> findAllByPosition_Id(UUID positionId);

    Optional<CandidatePositionMatch> findByCandidate_IdAndPosition_Id(UUID candidateId, UUID positionId);

    boolean existsByCandidate_IdAndPosition_Id(UUID candidateId, UUID positionId);
}
