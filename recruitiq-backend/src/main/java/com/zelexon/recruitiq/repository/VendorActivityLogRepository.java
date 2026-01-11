package com.zelexon.recruitiq.repository;

import com.zelexon.recruitiq.dao.VendorActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VendorActivityLogRepository extends JpaRepository<VendorActivityLog, UUID> {
    List<VendorActivityLog> findAllByVendor_Id(UUID vendorId);

    List<VendorActivityLog> findAllByVendor_IdAndCandidate_Id(UUID vendorId, UUID candidateId);

    List<VendorActivityLog> findAllByVendor_IdAndAction(UUID vendorId, String action);
}
