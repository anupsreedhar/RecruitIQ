package com.zelexon.recruitiq.repository;

import com.zelexon.recruitiq.dao.InterviewRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InterviewRequestRepository extends JpaRepository<InterviewRequest, UUID> {
    List<InterviewRequest> findAllByVendor_Id(UUID vendorId);

    List<InterviewRequest> findAllByCandidate_Id(UUID candidateId);

    List<InterviewRequest> findAllByPosition_Id(UUID positionId);

    List<InterviewRequest> findAllByVendor_IdAndStatus(UUID vendorId, String status);

    Optional<InterviewRequest> findByVendor_IdAndCandidate_IdAndPosition_Id(UUID vendorId, UUID candidateId, UUID positionId);
}
