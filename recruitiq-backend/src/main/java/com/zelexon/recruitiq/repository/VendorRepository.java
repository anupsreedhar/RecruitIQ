package com.zelexon.recruitiq.repository;

import com.zelexon.recruitiq.dao.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    Optional<Vendor> findByNameIgnoreCase(String name);

    List<Vendor> findAllByStatus(String status);
}
