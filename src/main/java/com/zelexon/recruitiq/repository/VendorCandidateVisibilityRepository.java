package com.zelexon.recruitiq.repository;

import com.zelexon.recruitiq.dao.VendorCandidateVisibility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VendorCandidateVisibilityRepository extends JpaRepository<VendorCandidateVisibility, UUID> {
    List<VendorCandidateVisibility> findAllByVendor_Id(UUID vendorId);

    List<VendorCandidateVisibility> findAllByCandidate_Id(UUID candidateId);

    List<VendorCandidateVisibility> findAllByPosition_Id(UUID positionId);

    boolean existsByVendor_IdAndCandidate_Id(UUID vendorId, UUID candidateId);

    Optional<VendorCandidateVisibility> findByVendor_IdAndCandidate_IdAndPosition_Id(UUID vendorId, UUID candidateId, UUID positionId);
}
