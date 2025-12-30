package com.zelexon.recruitiq.repository;

import com.zelexon.recruitiq.dao.VendorUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VendorUserRepository extends JpaRepository<VendorUser, UUID> {
    List<VendorUser> findAllByVendor_Id(UUID vendorId);

    Optional<VendorUser> findByVendor_IdAndUser_Id(UUID vendorId, UUID userId);

    boolean existsByVendor_IdAndUser_Id(UUID vendorId, UUID userId);
}
